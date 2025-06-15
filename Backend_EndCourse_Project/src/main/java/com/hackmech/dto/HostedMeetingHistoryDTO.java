package com.hackmech.dto;

public class HostedMeetingHistoryDTO {
    private Long meetingId;
    private String meetingTitle;
    private String meetingDate;
    private String startTime;
    private String endTime;
    private String roomName;
    private String attendeeName;
    private String invitationStatus;

    public HostedMeetingHistoryDTO(Long meetingId, String meetingTitle, String meetingDate, String startTime, String endTime, String roomName, String attendeeName, String invitationStatus) {
        this.meetingId = meetingId;
        this.meetingTitle = meetingTitle;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = roomName;
        this.attendeeName = attendeeName;
        this.invitationStatus = invitationStatus;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAttendeeName() {
        return attendeeName;
    }

    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }

    public String getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(String invitationStatus) {
        this.invitationStatus = invitationStatus;
    }
// Getters and Setters
}
