package com.hackmech.service;

import com.hackmech.dto.MeetingHistoryDTO;
import com.hackmech.repository.MeetingHistoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingHistoryService {

    @Autowired
    private MeetingHistoryDatabase meetingHistoryDatabase;

    public List<MeetingHistoryDTO> getMeetingHistoryForUser(Long userId) {
        return meetingHistoryDatabase.getPastMeetingsByHost(userId);
    }
    public List<MeetingHistoryDTO> getAttendedMeetingHistoryForUser(Long userId) {

        return meetingHistoryDatabase.getPastAttendedMeetings(userId);
    }

}