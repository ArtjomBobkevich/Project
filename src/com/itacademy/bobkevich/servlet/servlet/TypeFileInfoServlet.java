package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dao.TypeFileDao;
import com.itacademy.bobkevich.servlet.service.TypeFileService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/typefile-info")
public class TypeFileInfoServlet extends HttpServlet {

    private TypeFileService typeFileService = TypeFileService.getTypeFileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("typefile", typeFileService.findOne(Long.parseLong(id)));

        getServletContext()
                .getRequestDispatcher(JspPath.get("typefile-info"))
                .forward(req, resp);
    }
}
