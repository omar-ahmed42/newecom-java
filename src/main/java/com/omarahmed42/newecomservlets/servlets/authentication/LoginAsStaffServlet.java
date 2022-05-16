package com.omarahmed42.newecomservlets.servlets.authentication;

import com.omarahmed42.newecomservlets.ejbs.AuthenticationServiceImpl;
import com.omarahmed42.newecomservlets.entities.AdministratorEntity;
import com.omarahmed42.newecomservlets.entities.StaffEntity;
import com.omarahmed42.newecomservlets.enums.UserType;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginAsStaffServlet", value = "/staff/login")
public class LoginAsStaffServlet extends HttpServlet {

    @EJB
    private AuthenticationServiceImpl authenticationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/authentication/staff/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("user-id");
        String password = request.getParameter("user-password");

        StaffEntity user = (StaffEntity) authenticationService.login(id, password, UserType.STAFF);

        if (Objects.isNull(user)) {
            request.getRequestDispatcher("/staff/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("staffId", user.getStaffId());
            request.getRequestDispatcher("/staff/courses").forward(request, response);

        }

    }
}
