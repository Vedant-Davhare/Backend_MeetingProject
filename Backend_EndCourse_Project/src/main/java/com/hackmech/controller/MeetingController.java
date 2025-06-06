package com.hackmech.controller;

import com.hackmech.dto.MeetingDTO;
import com.hackmech.dto.MeetingRequestDTO;
import com.hackmech.entity.Role;
import com.hackmech.exception.UnauthorizedAccessException;
import com.hackmech.payload.ApiResponse;
import com.hackmech.service.MeetingService;
import com.hackmech.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private UserService userService;

    // BOOK MEETING - Only LEADERSHIP and TEAMLEAD can book
    @PostMapping("/book")
    public ResponseEntity<ApiResponse<MeetingDTO>> bookMeeting(@RequestBody MeetingRequestDTO request,
                                                               HttpServletRequest httpRequest) {
        Long userId = getUserIdFromCookie(httpRequest);

        // Get user and check role
        Role role = userService.getUserRoleById(userId);
        System.out.println("loggin used role: " + role);
        if (!(role == Role.LEADERSHIP || role == Role.TEAMLEAD)) {
            throw new UnauthorizedAccessException("Only Leadership or Teamlead can book meetings");
        }

        MeetingDTO bookedMeeting = meetingService.bookMeeting(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Meeting booked successfully", bookedMeeting));
    }

    // Helper method to get userId from cookie
    private Long getUserIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new UnauthorizedAccessException("User not logged in.");
        }

        for (Cookie cookie : cookies) {
            if ("userId".equals(cookie.getName())) {
                return Long.valueOf(cookie.getValue());
            }
        }

        throw new UnauthorizedAccessException("User not logged in.");
    }
}
