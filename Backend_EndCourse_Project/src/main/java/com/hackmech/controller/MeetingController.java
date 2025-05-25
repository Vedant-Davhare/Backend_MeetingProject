package com.hackmech.controller;

import com.hackmech.dto.MeetingDTO;
import com.hackmech.payload.ApiResponse;
import com.hackmech.service.MeetingService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping("/hosted")
    public ResponseEntity<ApiResponse<List<MeetingDTO>>> getMeetingsByHost(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Long userId = null;
//        Long userId = 3L;

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

        List<MeetingDTO> meetings = meetingService.getMeetingsByHostId(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Meetings fetched successfully", meetings));
    }

    @GetMapping("/attendees-meetings")
    public ResponseEntity<ApiResponse<List<MeetingDTO>>> getUserMeetings(@CookieValue("userId") Long userId) {
        List<MeetingDTO> meetings = meetingService.getMeetingsByAttendeeId(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Meetings fetched successfully", meetings));
    }

}
