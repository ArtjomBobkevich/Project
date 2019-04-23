package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.entity.Category;
import com.itacademy.bobkevich.servlet.entity.Genre;
import com.itacademy.bobkevich.servlet.service.CategoryService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value="/category-delete",name ="CategoryDeleteServlet")
public class CategoryDeleteServlet extends HttpServlet {

    private CategoryService categoryService = CategoryService.getCategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories",categoryService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("category-delete"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Category category = Category.builder()
                .name(req.getParameter("name"))
                .id(Long.parseLong(req.getParameter("categoryId")))
                .build();
        categoryService.delete(category);
        resp.sendRedirect("/delete-info");
    }
}
