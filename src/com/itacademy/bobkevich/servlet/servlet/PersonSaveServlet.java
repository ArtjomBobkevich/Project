package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.CreateNewPersonDto;
import com.itacademy.bobkevich.servlet.dto.CreateNewResourceDto;
import com.itacademy.bobkevich.servlet.dto.ViewPersonFullInfoDto;
import com.itacademy.bobkevich.servlet.dto.ViewResourceFullInfoDto;
import com.itacademy.bobkevich.servlet.entity.Category;
import com.itacademy.bobkevich.servlet.entity.Person;
import com.itacademy.bobkevich.servlet.entity.PersonRole;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
import com.itacademy.bobkevich.servlet.service.PersonService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/save-person")
public class PersonSaveServlet extends HttpServlet {

    private PersonService personService = PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher(JspPath.get("save-person"))  /*тупо перенаправление*/
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        CreateNewPersonDto newPerson = CreateNewPersonDto.builder()
                .login(req.getParameter("login_name"))
                .first_name(req.getParameter("firstName"))
                .last_name(req.getParameter("lastName"))
                .age(req.getParameter("age"))
                .mail(req.getParameter("mail"))
                .password(req.getParameter("password"))
                .personRole(PersonRole.builder()
                        .id(2L)
                        .nameOfRole("User")
                        .build())
                .build();
            personService.save(newPerson);

        resp.sendRedirect("/login");
    }
}
