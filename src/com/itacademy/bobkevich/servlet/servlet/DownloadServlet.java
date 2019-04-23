package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.ViewResourceBasicInfoDto;
import com.itacademy.bobkevich.servlet.service.ResourceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(value = "/download",name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {

    private ResourceService resourceService = com.itacademy.bobkevich.servlet.service.ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"AllResources.txt\"");
        resp.setContentType("application/octet-stream");

        List<ViewResourceBasicInfoDto> text = resourceService.findAll();
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        for (ViewResourceBasicInfoDto s : text) {
            ViewResourceBasicInfoDto a =s;
            resp.getWriter().write(a.toString()+System.lineSeparator());
        }
    }
}
