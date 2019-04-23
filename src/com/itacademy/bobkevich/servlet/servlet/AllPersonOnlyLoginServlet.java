package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.service.PersonService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personList-info")
public class AllPersonOnlyLoginServlet extends HttpServlet {

    private PersonService personService =PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("personList", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("personList-info"))
                .forward(req, resp);
    }
}
