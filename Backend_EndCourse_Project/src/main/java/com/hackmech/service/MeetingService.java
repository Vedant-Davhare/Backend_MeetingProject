package com.hackmech.service;

import com.hackmech.dto.MeetingDTO;
import com.hackmech.dto.RoomDTO;
import com.hackmech.entity.Meeting;
import com.hackmech.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;

    public List<MeetingDTO> getMeetingsByHostId(Long userId) {
        List<Meeting> meetings = meetingRepository.findByHostId(userId);

        return meetings.stream()
                .map(meeting -> new MeetingDTO(
                        meeting.getId(),
                        meeting.getTitle(),
                        meeting.getDescription(),
                        meeting.getStartTime(),
                        meeting.getEndTime(),
                        new RoomDTO(
                                meeting.getRoom().getId(),
                                meeting.getRoom().getName(),
                                meeting.getRoom().getLocation(),
                                meeting.getRoom().getCapacity()
                        )
                )).collect(Collectors.toList());
    }

}
