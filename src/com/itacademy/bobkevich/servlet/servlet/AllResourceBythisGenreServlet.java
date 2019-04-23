package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.ViewResourceBasicInfoDto;
import com.itacademy.bobkevich.servlet.entity.Resource;
import com.itacademy.bobkevich.servlet.service.GenreService;
import com.itacademy.bobkevich.servlet.service.ResourceService;
import com.itacademy.bobkevich.servlet.util.JspPath;
import com.itacademy.bobkevich.servlet.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.itacademy.bobkevich.servlet.util.StringUtils.isEmpty;

@WebServlet("/resources-by-genre-list")
public class AllResourceBythisGenreServlet extends HttpServlet {

    private ResourceService resourceService = ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genreId = req.getParameter("id");

        req.setAttribute("resource", resourceService.findAllByGenre(Long.parseLong(genreId)));

        getServletContext()
                .getRequestDispatcher(JspPath.get("resources-by-genre-list"))
                .forward(req, resp);
    }
}