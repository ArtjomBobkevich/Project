package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.service.GenreService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/genre-info")
public class GenreInfoServlet extends HttpServlet {

    private GenreService genreService = GenreService.getGenreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("genre", genreService.findOne(Long.parseLong(id)));

        getServletContext()
                .getRequestDispatcher(JspPath.get("genre-info"))
                .forward(req, resp);
    }
}
