package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.service.TypeFileService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/typefiles-list")
public class AllTypeFileServlet extends HttpServlet {

    private TypeFileService typeFileService = TypeFileService.getTypeFileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typefiles", typeFileService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("typefiles-list"))
                .forward(req, resp);
    }
}
