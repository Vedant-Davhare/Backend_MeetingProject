package com.hackmech.repository;

import com.hackmech.dto.MeetingHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MeetingHistoryDatabase {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MeetingHistoryDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ✅ Method to fetch meetings where user was an attendee and accepted
    public List<MeetingHistoryDTO> getPastAttendedMeetings(Long userId) {
        String sql = """
            SELECT 
                m.id AS meeting_id,
                m.title,
                m.description,
                m.meeting_date,
                m.start_time,
                m.end_time,
                m.status
            FROM meetings m
            JOIN meeting_attendees ma ON m.id = ma.meeting_id
            WHERE ma.user_id = ?
              AND ma.status = 'ACCEPTED'
              AND m.end_time < NOW()
            ORDER BY m.end_time DESC
        """;

        return jdbcTemplate.query(sql, new Object[]{userId}, new MeetingHistoryRowMapper());
    }

    // ✅ Method to fetch meetings hosted by a user in the past
    public List<MeetingHistoryDTO> getPastMeetingsByHost(Long userId) {
        String sql = """
            SELECT 
                m.id AS meeting_id,
                m.title,
                m.description,
                m.meeting_date,
                m.start_time,
                m.end_time,
                m.status
            FROM meetings m
            WHERE m.host_id = ?
              AND m.end_time < NOW()
            ORDER BY m.end_time DESC
        """;

        return jdbcTemplate.query(sql, new Object[]{userId}, new MeetingHistoryRowMapper());
    }

    // ✅ Common RowMapper for MeetingHistoryDTO
    private static class MeetingHistoryRowMapper implements RowMapper<MeetingHistoryDTO> {
        @Override
        public MeetingHistoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("meeting_id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            String date = rs.getString("meeting_date");
            String startTime = rs.getString("start_time").split(" ")[1];
            String endTime = rs.getString("end_time").split(" ")[1];
            String status = rs.getString("status");

            return new MeetingHistoryDTO(id, title, description, date, startTime, endTime, status);
        }
    }
}
