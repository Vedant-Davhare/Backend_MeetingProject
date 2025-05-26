package com.hackmech.controller;

import com.hackmech.dto.UserDTO;
import com.hackmech.entity.Role;
import com.hackmech.exception.UnauthorizedAccessException;
import com.hackmech.exception.UserNotLoggedInException;
import com.hackmech.payload.ApiResponse;
import com.hackmech.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDTO>> getCurrentUser(@CookieValue(value = "userId", required = false) Long userId) {
        if (userId == null) {
            throw new UserNotLoggedInException("User not logged in");
        }

        UserDTO userDTO = userService.getUserDtoById(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User fetched successfully", userDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<?>> getAllUsers(HttpServletRequest request) {
        Long userId = getUserIdFromCookie(request);

        UserDTO loggedInUser = userService.getUserDtoById(userId);
        Role userRole = loggedInUser.getRole();

        if (userRole != Role.LEADERSHIP && userRole != Role.TEAMLEAD) {
            throw new UnauthorizedAccessException("Access denied. Only Leadership or TeamLead allowed.");
        }

        List<UserDTO> users = userService.getAllUserDTOs();
        return ResponseEntity.ok(new ApiResponse<>(true, "Users fetched successfully", users));
    }

    // Utility method to extract userId from cookie
    private Long getUserIdFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) throw new UserNotLoggedInException("User not logged in.");;

        for (Cookie cookie : request.getCookies()) {
            if ("userId".equals(cookie.getName())) {
                return Long.valueOf(cookie.getValue());
            }
        }
        throw new UserNotLoggedInException("User not logged in.");
    }
}
