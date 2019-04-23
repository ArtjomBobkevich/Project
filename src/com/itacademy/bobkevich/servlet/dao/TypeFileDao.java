package com.itacademy.bobkevich.servlet.dao;

import com.itacademy.bobkevich.servlet.connection.ConnectionPool;
import com.itacademy.bobkevich.servlet.entity.Category;
import com.itacademy.bobkevich.servlet.entity.Comment;
import com.itacademy.bobkevich.servlet.entity.Genre;
import com.itacademy.bobkevich.servlet.entity.Person;
import com.itacademy.bobkevich.servlet.entity.Resource;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
//import com.itacademy.bobkevich.servlet.util.Connectionmanager;
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

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypeFileDao {

    private static final TypeFileDao TYPE_FILE_DAO = new TypeFileDao();
    private static final String FIND_ALL =
            "SELECT " +
                    "t.id AS id, " +
                    "t.name_of_type AS name " +
                    "FROM cloud_storage.type_file t ";
    private static final String FIND_ONE =
            "SELECT " +
                    "t.id AS id, " +
                    "t.name_of_type AS name " +
                    "FROM cloud_storage.type_file t " +
                    "WHERE t.id=?";
    private static final String DELETE = "DELETE FROM cloud_storage.type_file WHERE id=?";
    private static final String SAVE = "INSERT INTO cloud_storage.type_file (name_of_type) VALUES (?);";
    private static final String UPDATE = "UPDATE cloud_storage.type_file SET name_of_type=? WHERE id=?";

    @SneakyThrows
    private TypeFile getTypeFileFromResultSet(ResultSet resultSet) {
        return TypeFile.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    @SneakyThrows
    public List<TypeFile> findAll() {
        List<TypeFile> typeFiles = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                TypeFile typeFile = getTypeFileFromResultSet(resultSet);
                typeFiles.add(typeFile);
            }
        }
        return typeFiles;
    }

    @SneakyThrows
    public TypeFile save(TypeFile typeFile) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, typeFile.getName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                typeFile.setId(generatedKeys.getLong(1));
            }
        }
        return typeFile;
    }

    @SneakyThrows
    public TypeFile update(TypeFile typeFile) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, typeFile.getName());
            preparedStatement.setObject(2, typeFile.getId());

            preparedStatement.executeUpdate();
        }
        return typeFile;
    }

    @SneakyThrows
    public Optional<TypeFile> findOne(Long id) {
        Optional<TypeFile> typeFile = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                typeFile = Optional.of(getTypeFileFromResultSet(resultSet));
            }
        }
        return typeFile;
    }

    @SneakyThrows
    public boolean delete(TypeFile typeFile) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, typeFile.getId());

            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }

        return result;
    }

    public static TypeFileDao getTypeFileDao() {
        return TYPE_FILE_DAO;
    }
}
