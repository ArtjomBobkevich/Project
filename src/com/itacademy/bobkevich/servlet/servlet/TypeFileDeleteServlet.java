package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.entity.Genre;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
import com.itacademy.bobkevich.servlet.service.TypeFileService;
import com.itacademy.bobkevich.servlet.util.JspPath;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/typefile-delete",name = "TypeFileDelete")
public class TypeFileDeleteServlet extends HttpServlet {

    private TypeFileService typeFileService = TypeFileService.getTypeFileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typeFiles",typeFileService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("typefile-delete"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        TypeFile typeFile = TypeFile.builder()
                .id(Long.parseLong(req.getParameter("typeFileId")))
                .name(req.getParameter("name"))
                .build();
        typeFileService.delete(typeFile);
        resp.sendRedirect("/delete-info");
    }
}
