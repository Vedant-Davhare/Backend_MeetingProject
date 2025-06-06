package com.hackmech.repository;

import com.hackmech.entity.Meeting;
import com.hackmech.entity.MeetingStatus;
import com.hackmech.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findByHostId(Long hostId);

    @Query("SELECT m FROM Meeting m JOIN m.attendees a WHERE a.id = :userId")
    List<Meeting> findMeetingsByAttendeeId(@Param("userId") Long userId);

    @Query("SELECT m FROM Meeting m WHERE m.room.id = :roomId " +
            "AND m.startTime < :endTime AND m.endTime > :startTime")
    List<Meeting> findByRoomIdAndTimeRange(@Param("roomId") Long roomId,
                                           @Param("startTime") LocalDateTime startTime,
                                           @Param("endTime") LocalDateTime endTime);

    // ✅ New method to detect scheduling conflicts with a specific date and status
    @Query("SELECT m FROM Meeting m WHERE m.room = :room " +
            "AND m.meetingDate = :meetingDate " +
            "AND m.startTime < :endTime " +
            "AND m.endTime > :startTime " +
            "AND m.status = :status")
    List<Meeting> findScheduledMeetingsByRoomAndTime(@Param("room") Room room,
                                                     @Param("meetingDate") LocalDate meetingDate,
                                                     @Param("startTime") LocalDateTime startTime,
                                                     @Param("endTime") LocalDateTime endTime,
                                                     @Param("status") MeetingStatus status);
}
