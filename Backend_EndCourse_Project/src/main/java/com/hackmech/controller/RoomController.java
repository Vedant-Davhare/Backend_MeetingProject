package com.hackmech.controller;

import com.hackmech.dto.RoomDTO;
import com.hackmech.exception.UserNotLoggedInException;
import com.hackmech.payload.ApiResponse;
import com.hackmech.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoomDTO>>> getAllRooms(@CookieValue(value = "userId", required = false) Long userId) {

        if (userId == null) {
            throw new UserNotLoggedInException("User not logged in");
        }
        List<RoomDTO> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(new ApiResponse<>(true, "Rooms fetched successfully", rooms));
    }
}
