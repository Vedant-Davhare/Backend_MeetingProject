package com.hackmech.controller;

import com.hackmech.dto.MeetingDTO;
import com.hackmech.payload.ApiResponse;
import com.hackmech.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/me/meetings")
public class UserMeetingController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping("/hosted")
    public ResponseEntity<ApiResponse<List<MeetingDTO>>> getHostedMeetings(
            @CookieValue(value = "userId", required = false) Long userId) {

        if (userId == null) {
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false, "User not logged in", null));
        }

        List<MeetingDTO> meetings = meetingService.getMeetingsByHostId(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Hosted meetings fetched successfully", meetings));
    }

    @GetMapping("/attending")
    public ResponseEntity<ApiResponse<List<MeetingDTO>>> getAttendingMeetings(
            @CookieValue(value = "userId", required = false) Long userId) {

        if (userId == null) {
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false, "User not logged in", null));
        }

        List<MeetingDTO> meetings = meetingService.getMeetingsByAttendeeId(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Attending meetings fetched successfully", meetings));
    }
}