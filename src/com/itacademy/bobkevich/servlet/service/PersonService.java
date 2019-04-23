package com.itacademy.bobkevich.servlet.service;

import com.itacademy.bobkevich.servlet.dao.PersonDao;
import com.itacademy.bobkevich.servlet.dto.*;
import com.itacademy.bobkevich.servlet.entity.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {

    private static final PersonService PERSON_SERVICE = new PersonService();

    public ViewPersonFullInfoDto save(CreateNewPersonDto person) {
        Person savedPerson = PersonDao.getPersonDao().save(Person.builder()
                .login(person.getLogin())
                .first_name(person.getFirst_name())
                .last_name(person.getLast_name())
                .age(person.getAge())
                .mail(person.getMail())
                .password(person.getPassword())
                .personRole(person.getPersonRole())
                .build());

        return new ViewPersonFullInfoDto(
                savedPerson.getLogin(),
                savedPerson.getFirst_name(),
                savedPerson.getLast_name(),
                savedPerson.getAge(),
                savedPerson.getMail(),
                savedPerson.getPassword(),
                savedPerson.getPersonRole().getNameOfRole()
        );
    }

    public List<ViewPersonLogin> findAll() {
        return PersonDao.getPersonDao().findAll().stream()
                .map(it -> new ViewPersonLogin(it.getLogin()))
                .collect(Collectors.toList());
    }

    public ViewPersonFullInfoDto FindById(String login) {
        return PersonDao.getPersonDao().findById(login)
                .map(it -> ViewPersonFullInfoDto.builder()
                        .login(it.getLogin())
                        .first_name(it.getFirst_name())
                        .last_name(it.getLast_name())
                        .age(it.getAge())
                        .mail(it.getMail())
                        .password(it.getPassword())
                        .personRole(it.getPersonRole().getNameOfRole())
                        .build())
                .orElse(null);
    }

    public Optional<UserDto> FindByLogin(String login, String password) {
        return PersonDao.getPersonDao().findByLogin(login, password)
                .map(it -> UserDto.builder()
                        .login(it.getLogin())
                        .password(it.getPassword())
                        .personRole(it.getPersonRole().getNameOfRole())
                        .build());
    }

    public boolean delete(Person person) {
        return PersonDao.getPersonDao().delete(person);
    }

    public static PersonService getPersonService() {
        return PERSON_SERVICE;
    }
}