package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.CreateNewCategoryDto;
import com.itacademy.bobkevich.servlet.dto.CreateNewGenreDto;
import com.itacademy.bobkevich.servlet.dto.ViewGenreInfoDto;
import com.itacademy.bobkevich.servlet.service.CategoryService;
import com.itacademy.bobkevich.servlet.service.GenreService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/category-save",name = "CategorySaveServlet")
public class CategorySaveServlet extends HttpServlet {

    private CategoryService categoryService = CategoryService.getCategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher(JspPath.get("category-save"))  /*тупо перенаправление*/
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        CreateNewCategoryDto createNewCategoryDto = CreateNewCategoryDto.builder()
                .name(req.getParameter("name"))
                .build();

        CreateNewCategoryDto newCategory = categoryService.categorySave(createNewCategoryDto);
        resp.sendRedirect("/category-info?id=" + newCategory.getId());
    }
}
