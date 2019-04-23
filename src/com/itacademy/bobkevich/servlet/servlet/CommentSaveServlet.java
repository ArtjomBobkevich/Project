package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.*;
import com.itacademy.bobkevich.servlet.entity.Category;
import com.itacademy.bobkevich.servlet.entity.Comment;
import com.itacademy.bobkevich.servlet.entity.Person;
import com.itacademy.bobkevich.servlet.entity.Resource;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
import com.itacademy.bobkevich.servlet.service.CommentService;
import com.itacademy.bobkevich.servlet.service.ResourceService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/save-comment")
public class CommentSaveServlet extends HttpServlet {

    private CommentService commentService=CommentService.getCommentService();
    private ResourceService resourceService=ResourceService.getResourceService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("resources",resourceService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("save-comment"))  /*тупо перенаправление*/
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        CreateNewCommentDto newComment = CreateNewCommentDto.builder()
                .resourceId(Resource.builder()
                        .id(Long.parseLong(req.getParameter("resourceId")))
                        .resourceName(req.getParameter("name"))
                        .typeFile(TypeFile.builder()
                                .name("name_of_type_file")
                                .build())
                        .category(Category.builder()
                                .name("name_of_category")
                                .build())
                        .person(Person.builder()
                                .login("login")
                                .build())
                        .url("url")
                        .size("size")
                        .build())
                .text(req.getParameter("text"))
                .build();

        commentService.save(newComment);

        resp.sendRedirect("/resources-list");
    }
}
