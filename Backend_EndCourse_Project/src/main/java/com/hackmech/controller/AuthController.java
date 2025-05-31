package com.hackmech.controller;

import com.hackmech.dto.LoginRequest;
import com.hackmech.dto.UserDTO;
import com.hackmech.entity.User;
import com.hackmech.payload.ApiResponse;
import com.hackmech.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        boolean authenticated = userService.authenticate(request.getEmail(), request.getPassword());

        if (authenticated) {
            User user = userService.getUserByEmail(request.getEmail());

            Cookie userCookie = new Cookie("userId", String.valueOf(user.getId()));
            userCookie.setHttpOnly(true);
            userCookie.setPath("/");
            userCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
            response.addCookie(userCookie);

            UserDTO userDto = new UserDTO(user.getId(),user.getEmail(),user.getMobileNo(),user.getName(),user.getRole(),user.getDepartment().getName());

            return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", userDto));
        } else {
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false, "Invalid email or password", null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(HttpServletResponse response) {
        Cookie userCookie = new Cookie("userId", null);
        userCookie.setMaxAge(0);
        userCookie.setPath("/");
        response.addCookie(userCookie);

        return ResponseEntity.ok(new ApiResponse<>(true, "Logout successful", null));
    }
}
