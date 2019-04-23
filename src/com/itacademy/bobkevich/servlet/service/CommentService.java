package com.itacademy.bobkevich.servlet.service;

import com.itacademy.bobkevich.servlet.connection.ConnectionPool;
import com.itacademy.bobkevich.servlet.dao.CommentDao;
import com.itacademy.bobkevich.servlet.dao.GenreDao;
import com.itacademy.bobkevich.servlet.dto.*;
import com.itacademy.bobkevich.servlet.entity.Comment;
import com.itacademy.bobkevich.servlet.entity.Genre;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentService {

    private static final CommentService COMMENT_SERVICE = new CommentService();

    public List<CreateNewCommentDto> findAll() {
        return CommentDao.getCommentDao().findAll().stream()
                .map(it -> new CreateNewCommentDto(it.getId(),it.getResource_id(), it.getText()))
                .collect(Collectors.toList());
    }

    public List<CreateNewCommentDto> allByResource(Long resourceId) {
        return CommentDao.getCommentDao().findAllByResource(resourceId).stream()
                .map(it -> new CreateNewCommentDto(it.getId(), it.getResource_id(), it.getText()))
                .collect(Collectors.toList());
    }

    public ViewCommentFullINfoDto save(CreateNewCommentDto comment) {
        Comment savedComment = CommentDao.getCommentDao().commentSave(Comment.builder()
                        .id(comment.getId())
                        .resource_id(comment.getResourceId())
                        .text(comment.getText())
                        .build());

        return new ViewCommentFullINfoDto(
                savedComment.getId(),
                savedComment.getResource_id().getResourceName(),
                savedComment.getText()
        );
    }

    public boolean commentDelite(Comment comment) {
        return CommentDao.getCommentDao().commentDelete(comment);
    }

    public static CommentService getCommentService() {
        return COMMENT_SERVICE;
    }
}
