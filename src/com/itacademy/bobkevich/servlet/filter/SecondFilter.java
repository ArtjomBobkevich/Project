package com.itacademy.bobkevich.servlet.filter;

import com.itacademy.bobkevich.servlet.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(servletNames = {"CategoryDeleteServlet", "CategorySaveServlet", "CommentDeleteServlet", "GenreSaveServlet",
        "GenreDeleteServlet", "PersonDeleteServlet", "PersonInfoServlet", "ResourceDeleteServlet", "TypeFileDelete",
        "TypeFileSaveServlet","commentaries-list"})
public class SecondFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isUserHasPermissions(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String refererUri = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(nonNull(refererUri) ? refererUri : "/login");
        }
    }

    private boolean isUserHasPermissions(ServletRequest servletRequest) {
        UserDto userDto = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return nonNull(userDto) && userDto.getPersonRole().equals("Admin");
    }
}