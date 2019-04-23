package com.itacademy.bobkevich.servlet.service;

import com.itacademy.bobkevich.servlet.dao.GenreDao;
import com.itacademy.bobkevich.servlet.dao.TypeFileDao;
import com.itacademy.bobkevich.servlet.dto.CreateNewGenreDto;
import com.itacademy.bobkevich.servlet.dto.CreateNewTypeFileDto;
import com.itacademy.bobkevich.servlet.dto.ViewGenreInfoDto;
import com.itacademy.bobkevich.servlet.entity.Genre;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypeFileService {

    private static final TypeFileService TYPE_FILE_SERVICE = new TypeFileService();

    public List<CreateNewTypeFileDto> findAll() {
        return TypeFileDao.getTypeFileDao().findAll().stream()
                .map(it -> new CreateNewTypeFileDto(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    public CreateNewTypeFileDto save(CreateNewTypeFileDto typeFile) {
        TypeFile savedTypeFile = TypeFileDao.getTypeFileDao().save(
                TypeFile.builder()
                        .id(typeFile.getId())
                        .name(typeFile.getName())
                        .build());

        return new CreateNewTypeFileDto(savedTypeFile.getId(), savedTypeFile.getName());
    }

    public CreateNewTypeFileDto findOne(Long typeFileId) {
        return TypeFileDao.getTypeFileDao().findOne(typeFileId)
                .map(it -> CreateNewTypeFileDto.builder()
                        .name(it.getName())
                        .build())
                .orElse(null);
    }

    public boolean delete(TypeFile typeFile) {
        return TypeFileDao.getTypeFileDao().delete(typeFile);
    }

    public static TypeFileService getTypeFileService() {
        return TYPE_FILE_SERVICE;
    }
}