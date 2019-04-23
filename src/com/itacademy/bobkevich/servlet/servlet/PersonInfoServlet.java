package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.service.PersonService;
import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/person-info",name = "PersonInfoServlet")
public class PersonInfoServlet extends HttpServlet {

    private PersonService personService =PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        req.setAttribute("person", personService.FindById(login));

        getServletContext()
                .getRequestDispatcher(JspPath.get("person-info"))
                .forward(req, resp);
    }
}
