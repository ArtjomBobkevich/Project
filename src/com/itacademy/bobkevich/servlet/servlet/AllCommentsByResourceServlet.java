package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.CreateNewCommentDto;
import com.itacademy.bobkevich.servlet.service.CommentService;
import com.itacademy.bobkevich.servlet.util.JspPath;
import com.itacademy.bobkevich.servlet.util.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.itacademy.bobkevich.servlet.util.StringUtils.isEmpty;
import static junit.framework.Assert.assertTrue;

@WebServlet("/all-comment-by-resource")
public class AllCommentsByResourceServlet extends HttpServlet {

    private CommentService commentService = CommentService.getCommentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resourseId = req.getParameter("id");

        req.setAttribute("comment", commentService.allByResource(Long.parseLong(resourseId)));

        getServletContext()
                .getRequestDispatcher(JspPath.get("all-comment-by-resource"))
                .forward(req, resp);
    }
}
