package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.service.GenreService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/genres-list")
public class AllGenreInfoServlet extends HttpServlet {

    private GenreService genreService = GenreService.getGenreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genres", genreService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("genres-list"))
                .forward(req, resp);
    }
}
