package com.hackmech.repository;

import com.hackmech.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByHostId(Long hostId);

    @Query("SELECT m FROM Meeting m JOIN m.attendees a WHERE a.id = :userId")
    List<Meeting> findMeetingsByAttendeeId(@Param("userId") Long userId);

    @Query("SELECT m FROM Meeting m WHERE m.room.id = :roomId " +
            "AND m.startTime < :endTime AND m.endTime > :startTime")
    List<Meeting> findByRoomIdAndTimeRange(Long roomId, LocalTime startTime, LocalTime endTime);
}
