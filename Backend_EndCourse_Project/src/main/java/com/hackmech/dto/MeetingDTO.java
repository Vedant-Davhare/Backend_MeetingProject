package com.hackmech.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private RoomDTO room;
    private List<UserDTO> attendees;

    // Constructors
    public MeetingDTO() {}

    public MeetingDTO(Long id, String title, String description, LocalDateTime startTime, LocalDateTime endTime, RoomDTO room, List<UserDTO> attendees) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.attendees = attendees;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public List<UserDTO> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<UserDTO> attendees) {
        this.attendees = attendees;
    }
}
