package com.hackmech.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MeetingRequestDTO {
    private String title;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long roomId;
    private List<Long> attendeeIds;

    public MeetingRequestDTO() {
    }

    public MeetingRequestDTO(String title, String description, LocalTime startTime, LocalTime endTime, Long roomId, List<Long> attendeeIds) {
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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
