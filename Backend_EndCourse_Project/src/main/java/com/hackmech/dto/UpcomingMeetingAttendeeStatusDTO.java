package com.hackmech.dto;

public class UpcomingMeetingAttendeeStatusDTO {
    private Long userId;
    private String username;
    private String userRole;
    private String invitationStatus;
    private Long meetingId;
    private String meetingTitle;
    private String meetingDate;
    private String startTime;

    // Constructor
    public UpcomingMeetingAttendeeStatusDTO(Long userId, String username, String userRole,
                                            String invitationStatus, Long meetingId,
                                            String meetingTitle, String meetingDate, String startTime) {
        this.userId = userId;
        this.username = username;
        this.userRole = userRole;
        this.invitationStatus = invitationStatus;
        this.meetingId = meetingId;
        this.meetingTitle = meetingTitle;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(String invitationStatus) {
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
}
