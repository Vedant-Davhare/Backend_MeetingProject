package com.hackmech.controller;

import com.hackmech.dto.LoginRequest;
import com.hackmech.dto.UserDTO;
import com.hackmech.entity.User;
import com.hackmech.payload.ApiResponse;
import com.hackmech.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        boolean authenticated = userService.authenticate(request.getEmail(), request.getPassword());

        if (authenticated) {
            // Fetch the user from DB
            User user = userService.getUserByEmail(request.getEmail());

            // Set cookie (you can choose to store user.getId() or other safe identifier)
            Cookie userCookie = new Cookie("userId", String.valueOf(user.getId()));
            userCookie.setHttpOnly(true);
            userCookie.setPath("/"); // Accessible to the entire app
            userCookie.setMaxAge(1 * 24 * 60 * 60); // 7 days
            response.addCookie(userCookie);

            return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", null));
        } else {
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false, "Invalid email or password", null));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserDTO>> getProfile(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Long userId = null;

        for (Cookie cookie : cookies) {
            if ("userId".equals(cookie.getName())) {
                userId = Long.valueOf(cookie.getValue());
                break;
            }
        }

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "User not logged in", null));
        }

        UserDTO userDTO = userService.getUserDtoById(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User fetched successfully", userDTO));
    }
}
