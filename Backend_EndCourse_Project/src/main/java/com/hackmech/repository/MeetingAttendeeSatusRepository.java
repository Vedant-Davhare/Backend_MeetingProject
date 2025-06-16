package com.hackmech.repository;


import com.hackmech.entity.Meeting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MeetingAttendeeSatusRepository extends JpaRepository<Meeting, Long> {

    @Query(value = """
        SELECT 
            u.id AS userId,
            u.name AS username,
            u.role AS userRole,
            ma.status AS invitationStatus,
            m.id AS meetingId,
            m.title AS meetingTitle,
            DATE_FORMAT(m.meeting_date, '%Y-%m-%d') AS meetingDate,
            DATE_FORMAT(m.start_time, '%Y-%m-%d %H:%i:%s') AS startTime
        FROM 
            meetings m
        JOIN 
            meeting_attendees ma ON m.id = ma.meeting_id
        JOIN 
            user u ON ma.user_id = u.id
        WHERE 
            m.host_id = :hostId
            AND m.start_time > NOW()
            AND m.status = 'SCHEDULED'
        """, nativeQuery = true)
    List<Object[]> getUpcomingAttendeeStatusRaw(@Param("hostId") Long hostId);
}
