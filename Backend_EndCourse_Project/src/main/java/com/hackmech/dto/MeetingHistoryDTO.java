package com.hackmech.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MeetingHistoryDTO {
    private Long meetingId;
    private String title;
    private String description;
    private String meetingDate;
    private String startTime;
    private String endTime;
    private String status;

    // Constructors
    public MeetingHistoryDTO() {}

    public MeetingHistoryDTO(Long meetingId, String title, String description,
                             String meetingDate, String startTime,
                             String endTime, String status) {
        this.meetingId = meetingId;
        this.title = title;
        this.description = description;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters and setters
    public Long getMeetingId() { return meetingId; }
    public void setMeetingId(Long meetingId) { this.meetingId = meetingId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMeetingDate() { return meetingDate; }
    public void setMeetingDate(String meetingDate) { this.meetingDate = meetingDate; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String  getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}