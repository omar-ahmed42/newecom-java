package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.StudentServiceImpl;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminStudentsServlet", value = "/admin/students")
public class AdminStudentsServlet extends HttpServlet {

    @EJB
    private StudentServiceImpl studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentEntity> allStudents = studentService.findAllStudents();
        request.setAttribute("students", allStudents);
        RequestDispatcher rd = request.getRequestDispatcher("/admin/students.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
