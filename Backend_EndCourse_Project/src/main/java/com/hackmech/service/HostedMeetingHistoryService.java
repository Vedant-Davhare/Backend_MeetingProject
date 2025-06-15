package com.hackmech.service;

import com.hackmech.dto.HostedMeetingHistoryDTO;
import com.hackmech.repository.HostedMeetingHistoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostedMeetingHistoryService {

    private final HostedMeetingHistoryDatabase database;

    @Autowired
    public HostedMeetingHistoryService(HostedMeetingHistoryDatabase database) {
        this.database = database;
    }

    public List<HostedMeetingHistoryDTO> getHostedMeetingHistory(Long hostId) {
        return database.getHostedMeetingHistory(hostId);
    }
}
