package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.entity.Resource;
import com.itacademy.bobkevich.servlet.service.ResourceService;
import com.itacademy.bobkevich.servlet.util.JspPath;
import com.itacademy.bobkevich.servlet.util.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.itacademy.bobkevich.servlet.util.StringUtils.*;
import static org.junit.Assert.assertTrue;

@WebServlet("/resource-info")
public class ResourceInfoServlet extends HttpServlet {

    private ResourceService resourceService = ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("resource", resourceService.findOne(Long.parseLong(id)));

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-info"))
                .forward(req, resp);
    }
}