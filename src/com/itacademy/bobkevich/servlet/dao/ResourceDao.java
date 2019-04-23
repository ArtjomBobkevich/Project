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
import java.util.*;

import static java.sql.Statement.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceDao {

    private static final ResourceDao RESOURCE_DAO = new ResourceDao();
    private static final String FIND_ALL =
            "SELECT " +
                    "r.id AS resource_id, " +
                    "r.resource_name AS resource_name, " +
                    "r.type_id AS type_id, " +
                    "r.caterory_id AS category_id, " +
                    "r.login_who_giving AS login_who_giving, " +
                    "r.url AS url, " +
                    "r.file_size AS file_size, " +
                    "t.id AS type_file_id, " +
                    "t.name_of_type AS type_file_name, " +
                    "c.id AS category_id_at_category, " +
                    "c.category_name AS category_name_at_category, " +
                    "p.login AS person_login " +
                    "FROM cloud_storage.resource r " +
                    "INNER JOIN cloud_storage.type_file t " +
                    "ON r.type_id=t.id " +
                    "INNER JOIN cloud_storage.category c " +
                    "ON r.caterory_id=c.id " +
                    "INNER JOIN cloud_storage.person p " +
                    "ON r.login_who_giving=p.login ";
    private static final String FIND_ONE =
            FIND_ALL + "WHERE r.id=?";
    private static final String DELETE = "DELETE FROM cloud_storage.resource WHERE id=?";
    private static final String SAVE = "INSERT INTO cloud_storage.resource (resource_name, type_id , caterory_id, login_who_giving, url, file_size) VALUES (?,?/*(SELECT id FROM cloud_storage.type_file WHERE name_of_type=?)*/,?/*(SELECT id FROM cloud_storage.category WHERE category_name=?)*/,?,?,?);";
    private static final String UPDATE = "UPDATE cloud_storage.resource SET resource_name=?, type_id=?, caterory_id=?, login_who_giving=?, url=?,file_size=? WHERE id=?";
    private static final String ADD_GENRE = "INSERT INTO cloud_storage.resource_genre (resources_id, genre_id) VALUES (?/*(SELECT id FROM cloud_storage.resource WHERE resource_name=?)*/,?/*(SELECT id FROM cloud_storage.genre WHERE name_of_genre=?)*/);";
    private static final String GET_RESOURCES_BY_GENRE_ID = "SELECT " +
            "r.id AS resource_id, " +
            "r.resource_name AS resource_name, " +
            "r.type_id AS type_id, " +
            "r.caterory_id AS category_id, " +
            "r.login_who_giving AS login_who_giving, " +
            "r.url AS url, " +
            "r.file_size AS file_size, " +
            "t.id AS type_file_id, " +
            "t.name_of_type AS type_file_name, " +
            "c.id AS category_id_at_category, " +
            "c.category_name AS category_name_at_category, " +
            "p.login AS person_login " +
            "FROM cloud_storage.resource r " +
            "JOIN cloud_storage.resource_genre rg " +
            "ON r.id = rg.resources_id " +
            "LEFT JOIN cloud_storage.type_file t " +
            "ON r.type_id=t.id " +
            "LEFT JOIN cloud_storage.category c " +
            "ON r.caterory_id=c.id " +
            "LEFT JOIN cloud_storage.person p " +
            "ON r.login_who_giving=p.login " +
            "WHERE rg.genre_id=?";
    private static final String GET_RESOURCES_BY_CATEGORY_ID = "SELECT " +
            "cat.id AS id, " +
            "cat.category_name AS category_name, " +
            "r.id AS resource_id, " +
            "r.resource_name AS resource_name, " +
            "r.type_id AS type_id, " +
            "r.caterory_id AS category_id," +
            "r.login_who_giving AS login_who_giving, " +
            "r.url AS url, " +
            "r.file_size AS file_size, " +
            "t.id AS type_id, " +
            "t.name_of_type AS type_file_name, " +
            "p.login AS person_login " +
            "FROM cloud_storage.category cat " +
            "JOIN cloud_storage.resource r " +
            "ON r.caterory_id=cat.id " +
            "LEFT JOIN cloud_storage.type_file t " +
            "ON r.type_id=t.id " +
            "LEFT JOIN cloud_storage.person p " +
            "ON r.login_who_giving=p.login " +
            "WHERE cat.id=?";
    private static final String GET_RESOURCES_BY_TYPE_FILE_ID = "SELECT " +
            "t.id AS id, " +
            "t.name_of_type AS type_file_name, " +
            "r.id AS resource_id, " +
            "r.resource_name AS resource_name, " +
            "r.caterory_id AS category_id_at_resource, " +
            "r.login_who_giving AS login_who_giving, " +
            "r.url AS url, " +
            "r.file_size AS file_size, " +
            "c.id AS category_id_at_category, " +
            "c.category_name AS category_name, " +
            "p.login AS person_login " +
            "FROM cloud_storage.type_file t " +
            "JOIN cloud_storage.resource r " +
            "ON r.type_id=t.id " +
            "LEFT JOIN cloud_storage.category c " +
            "ON r.caterory_id=c.id " +
            "LEFT JOIN cloud_storage.person p " +
            "ON r.login_who_giving=p.login " +
            "WHERE t.id=?";

    @SneakyThrows
    public List<Resource> resourcesByTypeFile(Long typeFileId) {
        List<Resource> resources = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESOURCES_BY_TYPE_FILE_ID)) {
            preparedStatement.setLong(1, typeFileId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Resource resource = Resource.builder()
                        .id(resultSet.getLong("resource_id"))
                        .resourceName(resultSet.getString("resource_name"))
                        .typeFile(TypeFile.builder()
                                .id(resultSet.getLong("id"))
                                .name(resultSet.getString("type_file_name"))
                                .build())
                        .category(Category.builder()
                                .id(resultSet.getLong("category_id_at_category"))
                                .name(resultSet.getString("category_name"))
                                .build())
                        .person(Person.builder()
                                .login(resultSet.getString("person_login"))
                                .build())
                        .url(resultSet.getString("url"))
                        .size(resultSet.getString("file_size"))
                        .build();

                resources.add(resource);
            }
        }
        return resources;
    }

    @SneakyThrows
    public List<Resource> resourcesByGenre(Long genreId) {
        List<Resource> resources = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESOURCES_BY_GENRE_ID)) {
            preparedStatement.setLong(1, genreId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Resource resource = Resource.builder()
                        .id(resultSet.getLong("resource_id"))
                        .resourceName(resultSet.getString("resource_name"))
                        .typeFile(TypeFile.builder()
                                .id(resultSet.getLong("type_file_id"))
                                .name(resultSet.getString("type_file_name"))
                                .build())
                        .category(Category.builder()
                                .id(resultSet.getLong("category_id_at_category"))
                                .name(resultSet.getString("category_name_at_category"))
                                .build())
                        .person(Person.builder()
                                .login(resultSet.getString("person_login"))
                                .build())
                        .url(resultSet.getString("url"))
                        .size(resultSet.getString("file_size"))
                        .build();

                resources.add(resource);
            }
        }
        return resources;
    }

    @SneakyThrows
    public List<Resource> resourcesByCategory(Long categoryId) {
        List<Resource> resources = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESOURCES_BY_CATEGORY_ID)) {
            preparedStatement.setLong(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Resource resource = Resource.builder()
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
                        .build();

                resources.add(resource);
            }
        }
        return resources;
    }

    @SneakyThrows
    public Set<Genre> addGenre(Resource resource, Genre genre) {
        Set<Genre> genres = new HashSet<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_GENRE,RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, Optional.ofNullable(resource.getId()).orElse(null));
            preparedStatement.setObject(2, Optional.ofNullable(genre.getId()).orElse(null));

            preparedStatement.executeUpdate();
            genres.add(genre);
        }
        return genres;
    }

    @SneakyThrows
    public Resource save(Resource resource) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, resource.getResourceName());
            preparedStatement.setObject(2, Optional.ofNullable(resource.getTypeFile()).map(TypeFile::getId).orElse(null));
            preparedStatement.setObject(3, Optional.ofNullable(resource.getCategory()).map(Category::getId).orElse(null));
            preparedStatement.setObject(4, Optional.ofNullable(resource.getPerson()).map(Person::getLogin).orElse(null));
            preparedStatement.setObject(5, resource.getUrl());
            preparedStatement.setObject(6, resource.getSize());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                resource.setId(generatedKeys.getLong(1));
            }
        }
        return resource;
    }

    @SneakyThrows
    public Resource update(Resource resource) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, resource.getResourceName());
            preparedStatement.setObject(2, Optional.ofNullable(resource.getTypeFile()).map(TypeFile::getId).orElse(null));
            preparedStatement.setObject(3, Optional.ofNullable(resource.getCategory()).map(Category::getId).orElse(null));
            preparedStatement.setObject(4, Optional.ofNullable(resource.getPerson()).map(Person::getLogin).orElse(null));
            preparedStatement.setObject(5, resource.getUrl());
            preparedStatement.setObject(6, resource.getSize());
            preparedStatement.setObject(7, resource.getId());

            preparedStatement.executeUpdate();
        }
        return resource;
    }

    @SneakyThrows
    public List<Resource> findAll() {
        List<Resource> resources = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Resource resource = getResourceFromResultSet(resultSet);
                resources.add(resource);
            }
        }
        return resources;
    }

    @SneakyThrows
    public Optional<Resource> findById(Long id) {
        Optional<Resource> resource = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resource = Optional.of(getResourceFromResultSet(resultSet));
            }
        }
        return resource;
    }

    private Resource getResourceFromResultSet(ResultSet resultSet) throws SQLException {
        return Resource.builder()
                .id(resultSet.getLong("resource_id"))
                .resourceName(resultSet.getString("resource_name"))
                .typeFile(TypeFile.builder()
                        .id(resultSet.getLong("type_file_id"))
                        .name(resultSet.getString("type_file_name"))
                        .build())
                .category(Category.builder()
                        .id(resultSet.getLong("category_id_at_category"))
                        .name(resultSet.getString("category_name_at_category"))
                        .build())
                .person(Person.builder()
                        .login(resultSet.getString("person_login"))
                        .build())
                .url(resultSet.getString("url"))
                .size(resultSet.getString("file_size"))
                .build();
    }

    @SneakyThrows
    public boolean delete(Resource resource) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, resource.getId());

            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    public static ResourceDao getResourceDao() {
        return RESOURCE_DAO;
    }
}
