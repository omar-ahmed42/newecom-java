package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.StaffServiceImpl;
import com.omarahmed42.newecomservlets.entities.StaffEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "StaffCreationServlet", value = "/admin/staff-creation")
public class StaffCreationServlet extends HttpServlet {

    @EJB
    private StaffServiceImpl staffService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/staff/creation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String dateParameter = request.getParameter("dob");
        LocalDate dateOfBirth = LocalDate.parse(dateParameter);
        StaffEntity staff = new StaffEntity(firstName, lastName, dateOfBirth);
        staffService.addStaff(staff);
    }
}
