//package com.itacademy.bobkevich.servlet.filter;
//
//import com.itacademy.bobkevich.servlet.dto.UserDto;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static java.util.Objects.nonNull;
//
//@WebFilter("/*")
//public class FirstFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        if (isLoginPage(servletRequest)|| isUserHasPermissions(servletRequest)){
//            filterChain.doFilter(servletRequest,servletResponse);
//        } else {
//            ((HttpServletResponse) servletResponse).sendRedirect("/login");
//        }
//    }
//
//    private boolean isUserHasPermissions(ServletRequest servletRequest){
//        UserDto userDto=(UserDto)((HttpServletRequest)servletRequest).getSession().getAttribute("user");
//        return nonNull(userDto) && userDto.getPersonRole().equals("Admin");
//    }
//
//    private boolean isLoginPage (ServletRequest servletRequest){
//        String uri= ((HttpServletRequest)servletRequest).getRequestURI();
//        return uri.startsWith("/login");
//    }
//}

package com.itacademy.bobkevich.servlet.filter;

import com.itacademy.bobkevich.servlet.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/*")
public class FirstFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isLocalePage(servletRequest)||isRegistrationPage(servletRequest) ||isLoginPage(servletRequest)|| isUserAuthenticated(servletRequest)){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        }
    }

    private boolean isUserAuthenticated(ServletRequest servletRequest){
        UserDto userDto=(UserDto)((HttpServletRequest)servletRequest).getSession().getAttribute("user");
        return nonNull(userDto);
    }

    private boolean isLoginPage (ServletRequest servletRequest){
        String uri= ((HttpServletRequest)servletRequest).getRequestURI();
        return uri.startsWith("/login");
    }

    private boolean isRegistrationPage (ServletRequest servletRequest){
        String uri = ((HttpServletRequest)servletRequest).getRequestURI();
        return uri.contains("/save-person");
    }

    private boolean isLocalePage (ServletRequest servletRequest){
        String uri = ((HttpServletRequest)servletRequest).getRequestURI();
        return uri.contains("/locale");
    }
}
