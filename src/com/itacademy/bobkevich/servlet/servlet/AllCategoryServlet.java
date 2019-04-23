package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.service.CategoryService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/categories-list")
public class AllCategoryServlet extends HttpServlet {

    private CategoryService categoryService = CategoryService.getCategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("categories-list"))
                .forward(req, resp);
    }
}
