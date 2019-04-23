package com.itacademy.bobkevich.servlet.service;

import com.itacademy.bobkevich.servlet.dao.PersonRoleDao;
import com.itacademy.bobkevich.servlet.entity.PersonRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonRoleService {

    private static final PersonRoleService PERSON_ROLE_SERVICE = new PersonRoleService();

    public PersonRole save(PersonRole personRole) {
        return PersonRoleDao.getPersonRoleDao().save(personRole);
    }

    public PersonRole update(PersonRole personRole) {
        return PersonRoleDao.getPersonRoleDao().update(personRole);
    }

    public Optional<PersonRole> findOne(Integer id) {
        return PersonRoleDao.getPersonRoleDao().findOne(id);
    }

    public boolean delete(Integer id) {
        return PersonRoleDao.getPersonRoleDao().delete(id);
    }

    public static PersonRoleService getPersonRoleService() {
        return PERSON_ROLE_SERVICE;
    }
}
