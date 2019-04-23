package com.itacademy.bobkevich.servlet.dao;

import com.itacademy.bobkevich.servlet.connection.ConnectionPool;
import com.itacademy.bobkevich.servlet.entity.PersonRole;
//import com.itacademy.bobkevich.servlet.util.Connectionmanager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonRoleDao {

    private static final PersonRoleDao PERSON_ROLE_DAO = new PersonRoleDao();
    private static final String FIND_ONE =
            "SELECT " +
                    "p.id AS role_id, " +
                    "p.role AS role_name " +
                    "FROM cloud_storage.person_role p " +
                    "WHERE p.id=?";
    private static final String DELETE = "DELETE FROM cloud_storage.person_role WHERE id=?";
    private static final String SAVE = "INSERT INTO cloud_storage.person_role (role) VALUES (?);";
    private static final String UPDATE = "UPDATE cloud_storage.person_role SET role=? WHERE id=?";

    @SneakyThrows
    public PersonRole save(PersonRole personRole) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, personRole.getNameOfRole());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                personRole.setId(generatedKeys.getLong(1));
            }
        }
        return personRole;
    }

    @SneakyThrows
    public PersonRole update(PersonRole personRole) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, personRole.getNameOfRole());
            preparedStatement.setObject(2, personRole.getId());

            preparedStatement.executeUpdate();
        }
        return personRole;
    }

    @SneakyThrows
    public Optional<PersonRole> findOne(Integer id) {
        PersonRole personRole = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                personRole = PersonRole.builder()
                        .id(resultSet.getLong("role_id"))
                        .nameOfRole(resultSet.getString("role_name"))
                        .build();
            }
        }
        return Optional.ofNullable(personRole);
    }

    @SneakyThrows
    public boolean delete(Integer id) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }

        return result;
    }

    public static PersonRoleDao getPersonRoleDao() {
        return PERSON_ROLE_DAO;
    }
}
