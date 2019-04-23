package com.itacademy.bobkevich.servlet.service;

import com.itacademy.bobkevich.servlet.dao.CategoryDao;
import com.itacademy.bobkevich.servlet.dao.GenreDao;
import com.itacademy.bobkevich.servlet.dto.CreateNewCategoryDto;
import com.itacademy.bobkevich.servlet.dto.ViewGenreInfoDto;
import com.itacademy.bobkevich.servlet.entity.Category;
import com.itacademy.bobkevich.servlet.entity.Genre;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryService {

    private static final CategoryService CATEGORY_SERVICE = new CategoryService();

    public CreateNewCategoryDto categorySave(CreateNewCategoryDto category) {
        Category savedCategory = CategoryDao.getCategoryDao().categorySave(
                Category.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build());

        return new CreateNewCategoryDto(savedCategory.getId(), savedCategory.getName());
    }

    public List<CreateNewCategoryDto> findAll() {
        return CategoryDao.getCategoryDao().findAll().stream()
                .map(it -> new CreateNewCategoryDto(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    public CreateNewCategoryDto findOne(Long categoryId) {
        return CategoryDao.getCategoryDao().categoryFindOne(categoryId)
                .map(it -> CreateNewCategoryDto.builder()
                        .name(it.getName())
                        .build())
                .orElse(null);
    }

    public boolean delete(Category category) {
        return CategoryDao.getCategoryDao().categoryDelete(category);
    }

    public static CategoryService getCategoryService() {
        return CATEGORY_SERVICE;
    }
}
