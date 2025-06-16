package com.hackmech.controller;

import com.hackmech.dto.UpcomingMeetingAttendeeStatusDTO;
import com.hackmech.service.MeetingAttendeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingAttendeeStatusController {

    private final MeetingAttendeeService meetingService;

    public MeetingAttendeeStatusController(MeetingAttendeeService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/upcoming-attendees/{hostId}")
    public List<UpcomingMeetingAttendeeStatusDTO> getUpcomingAttendeeStatus(@PathVariable Long hostId) {
        return meetingService.getUpcomingAttendeeStatuses(hostId);
    }
}
