package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private final Connection connection;

    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS movies (
                id INT AUTO_INCREMENT PRIMARY KEY,
                title VARCHAR(200) NOT NULL,
                director VARCHAR(150) NOT NULL,
                release_year INT NOT NULL
            )
            """;
        try (Statement st = connection.createStatement()) {
            st.execute(sql);
        }
    }

    public int insert(Movie m) throws SQLException {
        String sql = "INSERT INTO movies (title, director, release_year) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getDirector());
            ps.setInt(3, m.getYear());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    m.setId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    public Movie findById(int id) throws SQLException {
        String sql = "SELECT id, title, director, release_year FROM movies WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Movie> findAll() throws SQLException {
        List<Movie> result = new ArrayList<>();
        String sql = "SELECT id, title, director, release_year FROM movies ORDER BY id";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(mapRow(rs));
            }
        }
        return result;
    }

    public boolean update(Movie m) throws SQLException {
        String sql = "UPDATE movies SET title = ?, director = ?, release_year = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getDirector());
            ps.setInt(3, m.getYear());
            ps.setInt(4, m.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Movie mapRow(ResultSet rs) throws SQLException {
        return new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("director"),
                rs.getInt("release_year")
        );
    }
}