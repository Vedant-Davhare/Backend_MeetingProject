package com.hackmech.repository;

import com.hackmech.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NotificationDatabase {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NotificationDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NotificationDTO> getUpcomingMeetingsForUser(Long userId) {
        String sql = """
            SELECT
                ma.id AS notification_id,
                ma.status,
                m.title AS meeting_title,
                m.meeting_date,
                m.start_time,
                m.end_time,
                m.description,
                r.name AS room_name,
                host.name AS host_name
            FROM meeting_attendees ma
            JOIN user u ON ma.user_id = u.id
            JOIN meetings m ON ma.meeting_id = m.id
            JOIN rooms r ON m.room_id = r.id
            JOIN user host ON m.host_id = host.id
            WHERE u.id = ?
              AND m.status = 'SCHEDULED'
              AND (m.meeting_date > CURDATE()
                   OR (m.meeting_date = CURDATE() AND m.end_time > NOW()))
            ORDER BY m.meeting_date ASC, m.start_time ASC
        """;

        return jdbcTemplate.query(sql, new Object[]{userId}, new NotificationRowMapper());
    }

    private static class NotificationRowMapper implements RowMapper<NotificationDTO> {
        @Override
        public NotificationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long notificationId = rs.getLong("notification_id");
            String status = rs.getString("status");
            String title = rs.getString("meeting_title");
            String date = rs.getString("meeting_date");
            String startTime = rs.getString("start_time").split(" ")[1];
            String endTime = rs.getString("end_time").split(" ")[1];
            String room = rs.getString("room_name");
            String host = rs.getString("host_name");
            String description = rs.getString("description");

            return new NotificationDTO(notificationId, title, date, startTime, endTime, room, host, status,description);
        }
    }

    public void updateInvitationStatus(Long notificationId, String status) {
        String sql = "UPDATE meeting_attendees SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status.toUpperCase(), notificationId);
    }
}
