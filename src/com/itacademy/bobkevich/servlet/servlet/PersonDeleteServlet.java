package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.entity.Genre;
import com.itacademy.bobkevich.servlet.entity.Person;
import com.itacademy.bobkevich.servlet.entity.PersonRole;
import com.itacademy.bobkevich.servlet.service.PersonService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/delete-person",name = "PersonDeleteServlet")
public class PersonDeleteServlet extends HttpServlet {

    private PersonService personService=PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("logins",personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("delete-person"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Person person = Person.builder()
                .login(req.getParameter("login"))
                .build();
        personService.delete(person);
        resp.sendRedirect("/delete-info");
    }
}
