package com.hackmech.service;



import com.hackmech.dto.UpcomingMeetingAttendeeStatusDTO;
import com.hackmech.repository.MeetingAttendeeSatusRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingAttendeeService {

    private final MeetingAttendeeSatusRepository meetingRepository;

    public MeetingAttendeeService(MeetingAttendeeSatusRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public List<UpcomingMeetingAttendeeStatusDTO> getUpcomingAttendeeStatuses(Long hostId) {
        List<Object[]> rawList = meetingRepository.getUpcomingAttendeeStatusRaw(hostId);
        List<UpcomingMeetingAttendeeStatusDTO> result = new ArrayList<>();

        for (Object[] obj : rawList) {
            result.add(new UpcomingMeetingAttendeeStatusDTO(
                    ((Number) obj[0]).longValue(),  // userId
                    (String) obj[1],                // username
                    (String) obj[2],                // userRole
                    (String) obj[3],                // invitationStatus
                    ((Number) obj[4]).longValue(),  // meetingId
                    (String) obj[5],                // meetingTitle
                    (String) obj[6],                // meetingDate
                    (String) obj[7]                 // startTime
            ));
        }
        return result;
    }
}
