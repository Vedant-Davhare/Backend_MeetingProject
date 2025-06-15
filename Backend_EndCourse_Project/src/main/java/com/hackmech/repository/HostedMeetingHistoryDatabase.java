package com.hackmech.repository;

import com.hackmech.dto.HostedMeetingHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HostedMeetingHistoryDatabase {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HostedMeetingHistoryDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HostedMeetingHistoryDTO> getHostedMeetingHistory(Long hostId) {
        String sql = """
            SELECT 
                m.id AS meeting_id,
                m.title AS meeting_title,
                m.meeting_date,
                m.start_time,
                m.end_time,
                r.name AS room_name,
                u.name AS attendee_name,
                ma.status AS invitation_status
            FROM meeting_attendees ma
            JOIN meetings m ON ma.meeting_id = m.id
            JOIN user u ON ma.user_id = u.id
            JOIN rooms r ON m.room_id = r.id
            WHERE m.host_id = ?
              AND (m.meeting_date < CURDATE() OR (m.meeting_date = CURDATE() AND m.end_time < NOW()))
            ORDER BY m.meeting_date DESC, m.start_time ASC
        """;

        return jdbcTemplate.query(sql, new Object[]{hostId}, new HostedMeetingHistoryRowMapper());
    }

    private static class HostedMeetingHistoryRowMapper implements RowMapper<HostedMeetingHistoryDTO> {
        @Override
        public HostedMeetingHistoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new HostedMeetingHistoryDTO(
                    rs.getLong("meeting_id"),
                    rs.getString("meeting_title"),
                    rs.getString("meeting_date"),
                    rs.getString("start_time").split(" ")[1],
                    rs.getString("end_time").split(" ")[1],
                    rs.getString("room_name"),
                    rs.getString("attendee_name"),
                    rs.getString("invitation_status")
            );
        }
    }
}
