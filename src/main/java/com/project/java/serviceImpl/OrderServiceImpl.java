package com.project.java.serviceImpl;

import com.project.java.Utils.DateUtils;
import com.project.java.dao.OrderRepository;
import com.project.java.dao.ProductsRepository;
import com.project.java.dao.UsersRepository;
import com.project.java.dto.OrderRequestDto;
import com.project.java.dto.OrdersDto;
import com.project.java.entity.OrderItems;
import com.project.java.entity.Orders;
import com.project.java.entity.Products;
import com.project.java.entity.Users;
import com.project.java.exception.ResourceNotFoundException;
import com.project.java.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public OrdersDto placeOrder(OrderRequestDto orderRequestDto) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(currentUserEmail);
        Users user = usersRepository.findById(orderRequestDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(DateUtils.formatDate());
        order.setStatus("PENDING");

        var orderItemsRequestDto = orderRequestDto.getItems();

        List<OrderItems> orderItems = orderItemsRequestDto.stream().map(orderItem -> {

            Products product = productsRepository.findById(orderItem.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

            if (product.getStockQuantity() < orderItem.getQuantity()) {
                throw new ResourceNotFoundException("Not enough stock for product " + product.getName());
            }

            OrderItems item = new OrderItems();
            item.setProduct(product);
            item.setOrder(order);
            item.setQuantity(orderItem.getQuantity());
            item.setPrice(product.getPrice());

            return item;
        }).toList();

        order.setOrderItems(orderItems);

        Double totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setTotalPrice(totalPrice);

        for (OrderItems orderItem : order.getOrderItems()) {
            Products product = orderItem.getProduct();

            int newStockQuantity = product.getStockQuantity() - orderItem.getQuantity();
            product.setStockQuantity(newStockQuantity);
            productsRepository.save(product);
        }

        Orders placedOrder = orderRepository.save(order);
        return mapper.map(placedOrder, OrdersDto.class);
    }
}
