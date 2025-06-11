package com.hackmech.controller;

import com.hackmech.dto.NotificationDTO;
import com.hackmech.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // ✅ Missing import
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/upcoming-meetings/{userId}")
    public List<NotificationDTO> getUpcomingMeetingNotifications(@PathVariable Long userId) {
        return notificationService.getUpcomingMeetingNotifications(userId);
    }

    @PutMapping("/respond/{notificationId}")
    public ResponseEntity<String> respondToInvitation(
            @PathVariable Long notificationId,
            @RequestParam String status) {
        notificationService.updateStatus(notificationId, status);
        return ResponseEntity.ok("Status updated to " + status);
    }
}
