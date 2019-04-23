package com.itacademy.bobkevich.servlet.service;

import com.itacademy.bobkevich.servlet.dao.GenreDao;
import com.itacademy.bobkevich.servlet.dao.ResourceDao;
import com.itacademy.bobkevich.servlet.dto.CreateNewGenreDto;
import com.itacademy.bobkevich.servlet.dto.ViewGenreInfoDto;
import com.itacademy.bobkevich.servlet.dto.ViewResourceBasicInfoDto;
import com.itacademy.bobkevich.servlet.entity.Genre;
import com.itacademy.bobkevich.servlet.entity.Resource;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreService {

    private static final GenreService GENRE_SERVICE = new GenreService();

    public List<ViewGenreInfoDto> findAll() {
        return GenreDao.getGenreDao().findAll().stream()
                .map(it -> new ViewGenreInfoDto(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    public ViewGenreInfoDto findOne(Long genreId) {
        return GenreDao.getGenreDao().findOne(genreId)
                .map(it -> ViewGenreInfoDto.builder()
                        .name(it.getName())
                        .build())
                .orElse(null);
    }

    public ViewGenreInfoDto save(CreateNewGenreDto genre) {
        Genre savedGenre = GenreDao.getGenreDao().save(
                Genre.builder()
                        .id(genre.getId())
                        .name(genre.getName())
                        .build());

        return new ViewGenreInfoDto(savedGenre.getId(), savedGenre.getName());
    }

    public boolean delete(Genre genre) {
        return GenreDao.getGenreDao().delete(genre);
    }

    public static GenreService getGenreService() {
        return GENRE_SERVICE;
    }
}
