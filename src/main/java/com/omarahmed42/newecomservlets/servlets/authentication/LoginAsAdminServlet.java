package com.omarahmed42.newecomservlets.servlets.authentication;

import com.omarahmed42.newecomservlets.ejbs.AuthenticationServiceImpl;
import com.omarahmed42.newecomservlets.entities.AdministratorEntity;
import com.omarahmed42.newecomservlets.enums.UserType;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginAsAdminServlet", value = "/admin/login")
public class LoginAsAdminServlet extends HttpServlet {

    @EJB
    private AuthenticationServiceImpl authenticationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/authentication/admin/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("user-id");
        String password = request.getParameter("user-password");
        AdministratorEntity user = (AdministratorEntity) authenticationService.login(id, password, UserType.ADMINISTRATOR);

        if (Objects.isNull(user)) {
            response.sendRedirect("login");
        } else {
            request.getSession().setAttribute("administratorId", user.getAdministratorId());
            response.sendRedirect("courses");
        }

    }
}
