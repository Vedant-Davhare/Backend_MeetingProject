package com.hackmech.controller;

import com.hackmech.dto.MeetingDTO;
import com.hackmech.dto.MeetingRequestDTO;
import com.hackmech.payload.ApiResponse;
import com.hackmech.service.MeetingService;
import com.hackmech.service.UserService;
import com.hackmech.exception.UnauthorizedAccessException;

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

    /**
     * Book a new meeting. Only LEADERSHIP and TEAMLEAD can book meetings.
     */
    @PostMapping("/book")
    public ResponseEntity<ApiResponse<MeetingDTO>> bookMeeting(@RequestBody MeetingRequestDTO request,
                                                               HttpServletRequest httpRequest) {
        Long userId = extractUserIdFromCookie(httpRequest);

        MeetingDTO bookedMeeting = meetingService.bookMeeting(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Meeting booked successfully", bookedMeeting));
    }

    /**
     * Extract user ID from the "userId" cookie in the request.
     *
     * @param request the HttpServletRequest containing cookies
     * @return user ID as Long
     */
    private Long extractUserIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            throw new UnauthorizedAccessException("User not logged in: no cookies found.");
        }

        for (Cookie cookie : cookies) {
            if ("userId".equals(cookie.getName())) {
                try {
                    return Long.parseLong(cookie.getValue());
                } catch (NumberFormatException e) {
                    throw new UnauthorizedAccessException("Invalid user ID in cookie.");
                }
            }
        }

        throw new UnauthorizedAccessException("User ID cookie not found. User not logged in.");
    }
}
