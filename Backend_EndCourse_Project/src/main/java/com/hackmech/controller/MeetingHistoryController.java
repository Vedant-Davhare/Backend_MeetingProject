package com.hackmech.controller;

import com.hackmech.dto.MeetingHistoryDTO;
import com.hackmech.service.MeetingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingHistoryController {

    @Autowired
    private MeetingHistoryService meetingHistoryService;

    @GetMapping("schedule/history/{userId}")
    public List<MeetingHistoryDTO> getMeetingHistory(@PathVariable Long userId) {
        return meetingHistoryService.getMeetingHistoryForUser(userId);
    }

    @GetMapping("attended/history/{userId}")
    public List<MeetingHistoryDTO> getAttendedMeetingHistory(@PathVariable Long userId){
        return meetingHistoryService.getAttendedMeetingHistoryForUser(userId);
    }
}
