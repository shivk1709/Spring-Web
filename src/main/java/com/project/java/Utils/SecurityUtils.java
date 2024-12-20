package com.project.java.Utils;

import com.project.java.entity.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof Users) {
            return (Users) authentication.getPrincipal();
        }
        return null; // No authenticated user
    }

    public static String getCurrentUsername() {
        Users user = getCurrentUser();
        return user != null ? user.getUsername() : null;
    }

}
