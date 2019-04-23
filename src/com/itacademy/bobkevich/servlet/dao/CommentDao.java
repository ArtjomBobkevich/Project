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
public class CommentDao {

    private static final CommentDao COMMENT_DAO = new CommentDao();
    private static final String FIND_ALL =
            "SELECT " +
                    "c.id AS comment_id, " +
                    "c.resource_id AS resource_id, " +
                    "c.text AS text, " +
                    "r.id AS id, " +
                    "r.resource_name AS resource_name, " +
                    "r.type_id AS type_id, " +
                    "r.caterory_id AS category_id, " +
                    "r.login_who_giving AS login_who_giving, " +
                    "r.url AS url, " +
                    "r.file_size AS file_size, " +
                    "t.id AS type_file_id, " +
                    "t.name_of_type AS type_file_name, " +
                    "cat.id AS category_id, " +
                    "cat.category_name AS category_name, " +
                    "p.login AS person_login " +
                    "FROM cloud_storage.comment c " +
                    "LEFT JOIN cloud_storage.resource r " +
                    "ON c.resource_id=r.id " +                  /*возможно нужно местами переставить*/
                    "LEFT JOIN cloud_storage.type_file t " +
                    "ON r.type_id=t.id " +
                    "LEFT JOIN cloud_storage.category cat " +
                    "ON r.caterory_id=cat.id " +
                    "LEFT JOIN cloud_storage.person p " +
                    "ON r.login_who_giving=p.login ";
    private static final String FIND_ONE =
            "SELECT " +
                    "c.id AS comment_id, " +
                    "c.resource_id AS resource_id, " +
                    "c.text AS text, " +
                    "r.id AS id, " +
                    "r.resource_name AS resource_name, " +
                    "r.type_id AS type_id, " +
                    "r.caterory_id AS category_id, " +
                    "r.login_who_giving AS login_who_giving, " +
                    "r.url AS url, " +
                    "r.file_size AS file_size, " +
                    "t.id AS type_file_id, " +
                    "t.name_of_type AS type_file_name, " +
                    "cat.id AS category_id, " +
                    "cat.category_name AS category_name, " +
                    "p.login AS person_login " +
                    "FROM cloud_storage.comment c " +
                    "LEFT JOIN cloud_storage.resource r " +
                    "ON c.resource_id=r.id " +                  /*возможно нужно местами переставить*/
                    "LEFT JOIN cloud_storage.type_file t " +
                    "ON r.type_id=t.id " +
                    "LEFT JOIN cloud_storage.category cat " +
                    "ON r.caterory_id=cat.id " +
                    "LEFT JOIN cloud_storage.person p " +
                    "ON r.login_who_giving=p.login " +
                    "WHERE c.id=?";
    private static final String DELETE = "DELETE FROM cloud_storage.comment WHERE id=?";
    private static final String SAVE = "INSERT INTO cloud_storage.comment  (resource_id, text) VALUES (?/*(SELECT id FROM cloud_storage.resource WHERE resource_name=?)*/,?);";
    private static final String UPDATE = "UPDATE cloud_storage.comment SET resource_id=?, text=? WHERE id=?";
    private static final String GET_COMMENTS_BY_RESOURCE_ID = "SELECT " +
            "com.id AS comment_id," +
            "com.resource_id AS comment_resource_id," +
            "com.text AS comment_text, " +
            "r.id AS resource_id, " +
            "r.resource_name AS resource_name, " +
            "r.type_id AS type_id_at_type, " +
            "r.caterory_id AS category_id, " +
            "r.login_who_giving AS login_who_giving, " +
            "r.url AS url, " +
            "r.file_size AS file_size, " +
            "t.id AS type_id, " +
            "t.name_of_type AS type_file_name, " +
            "c.id AS id, " +
            "c.category_name AS category_name, " +
            "p.login AS person_login " +
            "FROM cloud_storage.comment com " +
            "JOIN cloud_storage.resource r " +
            "ON com.resource_id=r.id " +
            "LEFT JOIN cloud_storage.type_file t " +
            "ON r.type_id=t.id " +
            "LEFT JOIN cloud_storage.category c " +
            "ON r.caterory_id=c.id " +
            "LEFT JOIN cloud_storage.person p " +
            "ON r.login_who_giving=p.login " +
            "WHERE r.id=?";

    @SneakyThrows
    private Comment getCommentFromResultSet(ResultSet resultSet) {
        return Comment.builder()
                .id(resultSet.getLong("comment_id"))
                .resource_id(Resource.builder()
                        .id(resultSet.getLong("id"))
                        .resourceName(resultSet.getString("resource_name"))
                        .typeFile(TypeFile.builder()
                                .id(resultSet.getLong("type_file_id"))
                                .name(resultSet.getString("type_file_name"))
                                .build())
                        .category(Category.builder()
                                .id(resultSet.getLong("category_id"))
                                .name(resultSet.getString("category_name"))
                                .build())
                        .person(Person.builder()
                                .login(resultSet.getString("person_login"))
                                .build())
                        .url(resultSet.getString("url"))
                        .size(resultSet.getString("file_size"))
                        .build())
                .text(resultSet.getString("text"))
                .build();
    }

    @SneakyThrows
    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Comment comment = getCommentFromResultSet(resultSet);
                comments.add(comment);
            }
        }
        return comments;
    }

    @SneakyThrows
    public List<Comment> findAllByResource(Long resourceId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COMMENTS_BY_RESOURCE_ID)) {
            preparedStatement.setLong(1, resourceId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = Comment.builder()
                        .id(resultSet.getLong("comment_id"))
                        .resource_id(Resource.builder()
                                .id(resultSet.getLong("resource_id"))
                                .resourceName(resultSet.getString("resource_name"))
                                .typeFile(TypeFile.builder()
                                        .id(resultSet.getLong("type_id"))
                                        .name(resultSet.getString("type_file_name"))
                                        .build())
                                .category(Category.builder()
                                        .id(resultSet.getLong("id"))
                                        .name(resultSet.getString("category_name"))
                                        .build())
                                .person(Person.builder()
                                        .login(resultSet.getString("person_login"))
                                        .build())
                                .url(resultSet.getString("url"))
                                .size(resultSet.getString("file_size"))
                                .build())
                        .text(resultSet.getString("comment_text"))
                        .build();

                comments.add(comment);
            }
        }
        return comments;
    }

    @SneakyThrows
    public Comment commentSave(Comment comment) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, Optional.ofNullable(comment.getResource_id()).map(Resource::getId).orElse(null));
            preparedStatement.setObject(2, comment.getText());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                comment.setId(generatedKeys.getLong(1));
            }
        }
        return comment;
    }

    @SneakyThrows
    public Comment commentUpdate(Comment comment) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setObject(1, Optional.ofNullable(comment.getResource_id()).map(Resource::getId).orElse(null));
            preparedStatement.setObject(2, comment.getText());
            preparedStatement.setObject(3, comment.getId());

            preparedStatement.executeUpdate();
        }
        return comment;
    }

    @SneakyThrows
    public Optional<Comment> findOne(Long id) {
        Optional<Comment> comment = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                comment = Optional.of(getCommentFromResultSet(resultSet));
            }
        }
        return comment;
    }

    @SneakyThrows
    public boolean commentDelete(Comment comment) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, comment.getId());

            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    public static CommentDao getCommentDao() {
        return COMMENT_DAO;
    }
}
