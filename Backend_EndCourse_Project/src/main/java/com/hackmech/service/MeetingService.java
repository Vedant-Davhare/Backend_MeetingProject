package com.hackmech.service;

import com.hackmech.dto.MeetingDTO;
import com.hackmech.dto.MeetingRequestDTO;
import com.hackmech.dto.RoomDTO;
import com.hackmech.dto.UserDTO;
import com.hackmech.entity.*;
import com.hackmech.exception.MeetingConflictException;
import com.hackmech.exception.ResourceNotFoundException;
import com.hackmech.exception.UnauthorizedAccessException;
import com.hackmech.exception.UserNotFoundException;
import com.hackmech.repository.MeetingRepository;
import com.hackmech.repository.RoomRepository;
import com.hackmech.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<MeetingDTO> getMeetingsByHostId(Long userId) {
        List<Meeting> meetings = meetingRepository.findByHostId(userId);
        return meetings.stream().map(this::convertToMeetingDTO).collect(Collectors.toList());
    }

    public List<MeetingDTO> getMeetingsByAttendeeId(Long userId) {
        List<Meeting> meetings = meetingRepository.findMeetingsByAttendeeId(userId);
        return meetings.stream().map(this::convertToMeetingDTO).collect(Collectors.toList());
    }

    private MeetingDTO convertToMeetingDTO(Meeting meeting) {
        return new MeetingDTO(
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
                ),
                meeting.getAttendees().stream().map(this::convertToUserDTO).collect(Collectors.toList())
        );
    }

    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getMobileNo(),
                user.getRole(),
                user.getDepartment().getName(),
                user.getCreatedAt()
        );
    }

    @Transactional
    public MeetingDTO bookMeeting(Long loggedInUserId, MeetingRequestDTO request) {
        User host = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (host.getRole() != Role.LEADERSHIP && host.getRole() != Role.TEAMLEAD) {
            throw new UnauthorizedAccessException("Only LEADERSHIP or TEAMLEAD can book meetings");
        }

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        LocalDate meetingDate = request.getStartTime().toLocalDate();

        // Check for conflicting scheduled meetings
        List<Meeting> conflictingMeetings = meetingRepository.findScheduledMeetingsByRoomAndTime(
                room,
                meetingDate,
                request.getStartTime(),
                request.getEndTime(),
                MeetingStatus.SCHEDULED
        );

        for (Meeting conflict : conflictingMeetings) {
            Role conflictingRole = conflict.getHost().getRole();
            if (host.getRole() == Role.LEADERSHIP && conflictingRole == Role.TEAMLEAD) {
                // Cancel TEAMLEAD's meeting
                conflict.setStatus(MeetingStatus.CANCELED);
                meetingRepository.save(conflict);
            } else {
                throw new MeetingConflictException("Meeting conflict with a higher or equal role meeting.");
            }
        }

        // Create and save the new meeting
        Meeting meeting = new Meeting();
        meeting.setTitle(request.getTitle());
        meeting.setDescription(request.getDescription());
        meeting.setStartTime(request.getStartTime());
        meeting.setEndTime(request.getEndTime());
        meeting.setMeetingDate(meetingDate);
        meeting.setRoom(room);
        meeting.setHost(host);
        meeting.setStatus(MeetingStatus.SCHEDULED);

        Set<User> attendees = new HashSet<>();
        for (Long id : request.getAttendeeIds()) {
            User attendee = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Attendee not found with ID: " + id));
            attendees.add(attendee);
        }
        meeting.setAttendees(attendees);

        Meeting createdMeeting = meetingRepository.save(meeting);
        return convertToMeetingDTO(createdMeeting);
    }
}
