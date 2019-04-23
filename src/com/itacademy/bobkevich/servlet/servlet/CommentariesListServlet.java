package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.service.CommentService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/commentaries-list",name = "commentaries-list")
public class CommentariesListServlet extends HttpServlet {

    private CommentService commentService = CommentService.getCommentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("commentaries", commentService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("commentaries-list"))
                .forward(req, resp);
    }
}
