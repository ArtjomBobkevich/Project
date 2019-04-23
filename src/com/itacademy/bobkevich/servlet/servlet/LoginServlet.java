package com.itacademy.bobkevich.servlet.servlet;

import com.itacademy.bobkevich.servlet.dto.UserDto;
import com.itacademy.bobkevich.servlet.service.PersonService;

import com.itacademy.bobkevich.servlet.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private PersonService personService=PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("login"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Optional<UserDto> user = personService.FindByLogin(req.getParameter("login"), req.getParameter("password"));
        if (user.isPresent()){
            req.getSession().setAttribute("user",user.get());
            resp.sendRedirect("/begin");
        } else {
            resp.sendRedirect("/login?login=%s&error=true");
        }
    }
}