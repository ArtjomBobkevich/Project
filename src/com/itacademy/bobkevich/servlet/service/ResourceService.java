package com.itacademy.bobkevich.servlet.service;

import com.itacademy.bobkevich.servlet.dao.ResourceDao;
import com.itacademy.bobkevich.servlet.dto.*;
import com.itacademy.bobkevich.servlet.entity.Genre;
import com.itacademy.bobkevich.servlet.entity.Resource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceService {

    private static final ResourceService RESOURCE_SERVICE = new ResourceService();

    public ViewResourceFullInfoDto save(CreateNewResourceDto resource) {
        Resource savedResource = ResourceDao.getResourceDao().save(Resource.builder()
                .id(resource.getId())
                .resourceName(resource.getResourceName())
                .typeFile(resource.getTypeFile())
                .category(resource.getCategory())
                .person(resource.getPerson())
                .url(resource.getUrl())
                .size(resource.getSize())
                .build());
        return new ViewResourceFullInfoDto(
                savedResource.getId(),
                savedResource.getResourceName(),
                savedResource.getTypeFile().getName(),
                savedResource.getCategory().getName(),
                savedResource.getPerson().getLogin(),
                savedResource.getUrl(),
                savedResource.getSize());
    }

    public List<ViewGenreInfoDto> addGenre(CreateNewResourceDto resource, CreateNewGenreDto genre) {

        Resource resourceAddGenre = Resource.builder()
                .id(resource.getId())
                .resourceName(resource.getResourceName())
                .typeFile(resource.getTypeFile())
                .category(resource.getCategory())
                .person(resource.getPerson())
                .url(resource.getUrl())
                .size(resource.getSize())
                .build();

        Genre genreAdd = Genre.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
        return ResourceDao.getResourceDao().addGenre(resourceAddGenre,genreAdd).stream()
                .map(it->new ViewGenreInfoDto(it.getId(),it.getName()))
                .collect(Collectors.toList());
    }

    public Resource update(Resource resource) {
        return ResourceDao.getResourceDao().update(resource);
    }

    public List<ViewResourceBasicInfoDto> findAllByTypeFile(Long typeFileId) {
        return ResourceDao.getResourceDao().resourcesByTypeFile(typeFileId).stream()
                .map(it -> new ViewResourceBasicInfoDto(it.getId(), it.getResourceName(), it.getTypeFile().getName(), it.getCategory().getName()))
                .collect(Collectors.toList());
    }

    public List<ViewResourceBasicInfoDto> findAllByGenre(Long genreId) {
        return ResourceDao.getResourceDao().resourcesByGenre(genreId).stream()
                .map(it -> new ViewResourceBasicInfoDto(it.getId(), it.getResourceName(), it.getTypeFile().getName(), it.getCategory().getName()))
                .collect(Collectors.toList());
    }

    public List<ViewResourceBasicInfoDto> findAllByCategory(Long categoryId) {
        return ResourceDao.getResourceDao().resourcesByCategory(categoryId).stream()
                .map(it -> new ViewResourceBasicInfoDto(it.getId(), it.getResourceName(), it.getTypeFile().getName(), it.getCategory().getName()))
                .collect(Collectors.toList());
    }

    public List<ViewResourceBasicInfoDto> findAll() {
        return ResourceDao.getResourceDao().findAll().stream()
                .map(it -> new ViewResourceBasicInfoDto(it.getId(), it.getResourceName(), it.getTypeFile().getName(), it.getCategory().getName()))
                .collect(Collectors.toList());
    }

    public ViewResourceFullInfoDto findOne(Long resourceId) {
        return ResourceDao.getResourceDao().findById(resourceId)
                .map(it -> ViewResourceFullInfoDto.builder()
                        .id(it.getId())   /*внёс правку*/
                        .resourceName(it.getResourceName())
                        .typeFile(it.getTypeFile().getName())
                        .category(it.getCategory().getName())
                        .person(it.getPerson().getLogin())
                        .url(it.getUrl())
                        .size(it.getSize())
                        .build())
                .orElse(null);
    }

    public boolean delete(Resource resource) {
        return ResourceDao.getResourceDao().delete(resource);
    }

    public static ResourceService getResourceService() {
        return RESOURCE_SERVICE;
    }
}
