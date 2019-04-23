package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.CreateNewResourceDto;
import com.itacademy.bobkevich.servlet.dto.ViewResourceFullInfoDto;
import com.itacademy.bobkevich.servlet.entity.Category;
import com.itacademy.bobkevich.servlet.entity.Person;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
import com.itacademy.bobkevich.servlet.service.CategoryService;
import com.itacademy.bobkevich.servlet.service.PersonService;
import com.itacademy.bobkevich.servlet.service.ResourceService;
import com.itacademy.bobkevich.servlet.service.TypeFileService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/save-resource")
public class ResourceSaveServlet extends HttpServlet {

    private ResourceService resourceService = ResourceService.getResourceService();
    private TypeFileService typeFileService = TypeFileService.getTypeFileService();
    private CategoryService categoryService = CategoryService.getCategoryService();
    private PersonService personService = PersonService.getPersonService();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typefiles",typeFileService.findAll());
        req.setAttribute("categories",categoryService.findAll());
        req.setAttribute("logins",personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("save-resource"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        CreateNewResourceDto resource = CreateNewResourceDto.builder()
                .resourceName(req.getParameter("name"))
                .typeFile(TypeFile.builder()
                        .id(Long.parseLong(req.getParameter("typeFileId")))
                        .name(req.getParameter("name_of_type_file"))
                        .build())
                .category(Category.builder()
                        .id(Long.parseLong(req.getParameter("categoryId")))
                        .name(req.getParameter("name_of_category"))
                        .build())
                .person(Person.builder()
                        .login(req.getSession().getAttribute("user").toString())
                        .build())
                .url(req.getParameter("url"))
                .size(req.getParameter("size"))
                .build();

        ViewResourceFullInfoDto savedResource = resourceService.save(resource);
        resp.sendRedirect("/resource-info?id=" + savedResource.getId());
    }
}
