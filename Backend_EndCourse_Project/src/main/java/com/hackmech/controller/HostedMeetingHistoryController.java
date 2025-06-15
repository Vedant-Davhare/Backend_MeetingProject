package com.hackmech.controller;

import com.hackmech.dto.HostedMeetingHistoryDTO;
import com.hackmech.service.HostedMeetingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HostedMeetingHistoryController {

    private final HostedMeetingHistoryService historyService;

    @Autowired
    public HostedMeetingHistoryController(HostedMeetingHistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/hosted/{hostId}")
    public List<HostedMeetingHistoryDTO> getHostedMeetingHistory(@PathVariable Long hostId) {
        return historyService.getHostedMeetingHistory(hostId);
    }
}
