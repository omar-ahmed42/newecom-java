package com.omarahmed42.newecomservlets.servlets.authentication;

import com.omarahmed42.newecomservlets.ejbs.AuthenticationServiceImpl;
import com.omarahmed42.newecomservlets.entities.StaffEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.UserType;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginAsStudentServlet", value = "/student/login")
public class LoginAsStudentServlet extends HttpServlet {

    @EJB
    private AuthenticationServiceImpl authenticationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/authentication/student/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("user-id");
        String password = request.getParameter("user-password");

        StudentEntity user = (StudentEntity) authenticationService.login(id, password, UserType.STUDENT);

        if (Objects.isNull(user)) {
            request.getRequestDispatcher("/student/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("studentId", user.getStudentId());
            request.getSession().setAttribute("academicYear", user.getAcademicYear());
            request.getRequestDispatcher("/student/courses").forward(request, response);

        }

    }
}
