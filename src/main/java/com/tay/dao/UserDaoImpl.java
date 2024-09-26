package com.tay.dao;

import com.tay.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail());
    }

    @Override
    public void updateUser(int id, User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, user.getName(), user.getEmail(), id);

        if (rowsAffected == 0) {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId);

        if (rowsAffected == 0) {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, userRowMapper())
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
    }
}
