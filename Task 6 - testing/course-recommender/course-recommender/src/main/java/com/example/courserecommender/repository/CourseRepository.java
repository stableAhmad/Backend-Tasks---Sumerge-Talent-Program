package com.example.courserecommender.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.courserecommender.model.Course;

@Repository
public class CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int testConnection() {
        return jdbcTemplate.queryForObject("SELECT 1", Integer.class);
    }

    public void insertCourse(Course course) {
        String sql = "INSERT INTO course (name, description, credit) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, course.getName(), course.getDescription(), course.getCredit());
    }

    public int updateCourse(Course course) {
        String sql = "UPDATE course SET name = ?, description = ?, credit = ? WHERE id = ?";
        return jdbcTemplate.update(sql, course.getName(), course.getDescription(), course.getCredit(), course.getId());
    }

    public Course getCourseById(int id) {
        String sql = "SELECT id, name, description, credit FROM course WHERE id = ?";

        return jdbcTemplate.query(
                sql,
                ps -> ps.setInt(1, id), // set parameter
                rs -> {
                    if (rs.next()) {
                        Course res = new Course(rs.getString("name"), rs.getString("description"), rs.getInt("credit"));
                        res.setId(rs.getString("id"));
                        return res;
                    } else {
                        return null; // course not found
                    }
                });
    }

    public int deleteCourse(int id) {
        String sql = "DELETE FROM course WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
