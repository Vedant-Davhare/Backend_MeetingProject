package com.hackmech.dto;

public class NotificationDTO {
    private String title;
    private String date;
    private String startTime;
    private String endTime;
    private String roomName;
    private String hostName;
    private String message;
    private Long notificationId;
    private String status;


    public NotificationDTO() {}

    public NotificationDTO(Long notificationId, String title, String date, String startTime, String endTime, String roomName, String hostName, String status) {
        this.notificationId = notificationId;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = roomName;
        this.hostName = hostName;
        this.status = status;
        this.message = buildMessage();
    }

// Getters and Setters for notificationId and status


    private String buildMessage() {
        return "Meeting: " + title + " | Date: " + date + " | Time: " +
                startTime + " - " + endTime + " | Room: " + roomName + " | Host: " + hostName;
    }

    // Getters and Setters

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public String getHostName() { return hostName; }
    public void setHostName(String hostName) { this.hostName = hostName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}