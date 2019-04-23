package com.itacademy.bobkevich.servlet.dao;

import com.itacademy.bobkevich.servlet.connection.ConnectionPool;
import com.itacademy.bobkevich.servlet.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreDao {

    private static final GenreDao GENRE_DAO = new GenreDao();
    private static final String FIND_ALL =
            "SELECT " +
                    "g.id AS id, " +
                    "g.name_of_genre AS name " +
                    "FROM cloud_storage.genre g ";
    private static final String FIND_ONE =
            "SELECT " +
                    "g.id AS id, " +
                    "g.name_of_genre AS name " +
                    "FROM cloud_storage.genre g " +
                    "WHERE g.id=?";
    private static final String SAVE = "INSERT INTO cloud_storage.genre (name_of_genre) VALUES (?)";
    private static final String DELETE = "DELETE FROM cloud_storage.genre WHERE id=?";
    private static final String UPDATE = "UPDATE cloud_storage.genre SET name_of_genre=? WHERE id=?";

    @SneakyThrows
    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Genre genre = getGenreFromResultSet(resultSet);
                genres.add(genre);
            }
        }
        return genres;
    }

    private Genre getGenreFromResultSet(ResultSet resultSet) throws SQLException {
        return Genre.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    @SneakyThrows
    public Optional<Genre> findOne(Long id) {
        Optional<Genre> genre = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                genre = Optional.of(getGenreFromResultSet(resultSet));
            }
        }
        return genre;
    }

    @SneakyThrows
    public Genre save(Genre genre) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, genre.getName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                genre.setId(generatedKeys.getLong(1));
            }
        }
        return genre;
    }

    @SneakyThrows
    public Genre update(Genre genre) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setObject(2, genre.getId());

            preparedStatement.executeUpdate();
        }
        return genre;
    }

    @SneakyThrows
    public boolean delete(Genre genre) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, genre.getId());

            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    public static GenreDao getGenreDao() {
        return GENRE_DAO;
    }
}
