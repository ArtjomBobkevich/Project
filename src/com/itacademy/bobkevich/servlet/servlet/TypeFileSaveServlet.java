package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.CreateNewGenreDto;
import com.itacademy.bobkevich.servlet.dto.CreateNewTypeFileDto;
import com.itacademy.bobkevich.servlet.dto.ViewGenreInfoDto;
import com.itacademy.bobkevich.servlet.service.TypeFileService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/typefile-save",name = "TypeFileSaveServlet")
public class TypeFileSaveServlet extends HttpServlet {

    private TypeFileService typeFileService = TypeFileService.getTypeFileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher(JspPath.get("typefile-save"))  /*тупо перенаправление*/
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        CreateNewTypeFileDto newTypeFile = CreateNewTypeFileDto.builder()
                .name(req.getParameter("name"))
                .build();

        CreateNewTypeFileDto savedTypeFile = typeFileService.save(newTypeFile);
        resp.sendRedirect("/typefile-info?id=" + savedTypeFile.getId());
    }
}
