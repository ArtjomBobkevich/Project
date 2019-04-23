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

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDao {

    private static final CategoryDao CATEGORY_DAO = new CategoryDao();
    private static final String FIND_ALL =
            "SELECT " +
                    "c.id AS id, " +
                    "category_name AS name " +
                    "FROM cloud_storage.category c ";
    private static final String FIND_ONE =
            "SELECT " +
                    "c.id AS category_id, " +
                    "c.category_name AS category_name " +
                    "FROM cloud_storage.category c " +
                    "WHERE c.id=?";
    private static final String DELETE = "DELETE FROM cloud_storage.category WHERE id=?";
    private static final String SAVE = "INSERT INTO cloud_storage.category  (category_name) VALUES (?);";
    private static final String UPDATE = "UPDATE cloud_storage.category SET category_name=? WHERE id=?";

    @SneakyThrows
    private Category getCategoryFromResultSet(ResultSet resultSet) {
        return Category.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    @SneakyThrows
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Category category = getCategoryFromResultSet(resultSet);
                categories.add(category);
            }
        }
        return categories;
    }

    @SneakyThrows
    public Category categorySave(Category category) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, category.getName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getLong(1));
            }
        }
        return category;
    }

    @SneakyThrows
    public Category categoryUpdate(Category category) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setObject(1, category.getName());
            preparedStatement.setObject(2, category.getId());

            preparedStatement.executeUpdate();
        }
        return category;
    }

    @SneakyThrows
    public Optional<Category> categoryFindOne(Long id) {
        Category category = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = Category.builder()
                        .id(resultSet.getLong("category_id"))
                        .name(resultSet.getString("category_name"))
                        .build();
            }
        }
        return Optional.ofNullable(category);
    }

    @SneakyThrows
    public boolean categoryDelete(Category category) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, category.getId());

            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    public static CategoryDao getCategoryDao() {
        return CATEGORY_DAO;
    }
}
