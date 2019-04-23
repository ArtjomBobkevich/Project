package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.ViewResourceBasicInfoDto;
import com.itacademy.bobkevich.servlet.service.ResourceService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.itacademy.bobkevich.servlet.util.StringUtils.isEmpty;

@WebServlet("/resources-by-typefile-list")
public class AllResourcesByThisTypeFileServlet extends HttpServlet {

    private ResourceService resourceService = ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeFileId = req.getParameter("id");

        req.setAttribute("resource", resourceService.findAllByTypeFile(Long.parseLong(typeFileId)));

        getServletContext()
                .getRequestDispatcher(JspPath.get("resources-by-typefile-list"))
                .forward(req, resp);
    }
}
