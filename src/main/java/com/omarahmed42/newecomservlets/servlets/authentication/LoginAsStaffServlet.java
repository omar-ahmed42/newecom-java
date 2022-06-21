package com.omarahmed42.newecomservlets.servlets.authentication;

import com.omarahmed42.newecomservlets.ejbs.AuthenticationServiceImpl;
import com.omarahmed42.newecomservlets.entities.StaffEntity;
import com.omarahmed42.newecomservlets.enums.UserType;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            response.sendRedirect("login");
        } else {
            request.getSession().setAttribute("staffId", user.getStaffId());
            response.sendRedirect("courses");
        }

    }
}
