package com.hackmech.service;

import com.hackmech.dto.NotificationDTO;
import com.hackmech.repository.NotificationDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationDatabase notificationDatabase;

    @Autowired
    public NotificationService(NotificationDatabase notificationDatabase) {
        this.notificationDatabase = notificationDatabase;
    }

    public List<NotificationDTO> getUpcomingMeetingNotifications(Long userId) {
        return notificationDatabase.getUpcomingMeetingsForUser(userId);
    }
    public void updateStatus(Long notificationId, String status) {
        notificationDatabase.updateInvitationStatus(notificationId, status);
    }

}