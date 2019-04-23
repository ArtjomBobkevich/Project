package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.CreateNewGenreDto;
import com.itacademy.bobkevich.servlet.dto.ViewGenreInfoDto;
import com.itacademy.bobkevich.servlet.entity.Genre;
import com.itacademy.bobkevich.servlet.service.GenreService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/delete-genre",name = "GenreDeleteServlet")
public class GenreDeleteServlet extends HttpServlet {

    private GenreService genreService = GenreService.getGenreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genres",genreService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("delete-genre"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Genre genre = Genre.builder()
                .id(Long.parseLong(req.getParameter("genreId")))
                .name(req.getParameter("name"))
                .build();
        genreService.delete(genre);
        resp.sendRedirect("/delete-info");
    }
}
