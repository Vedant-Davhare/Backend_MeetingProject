package com.hackmech.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingRequestDTO {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long roomId;
    private List<Long> attendeeIds;

    public MeetingRequestDTO() {
    }

    public MeetingRequestDTO(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Long roomId, List<Long> attendeeIds) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.attendeeIds = attendeeIds;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public List<Long> getAttendeeIds() {
        return attendeeIds;
    }

    public void setAttendeeIds(List<Long> attendeeIds) {
        this.attendeeIds = attendeeIds;
    }
}
