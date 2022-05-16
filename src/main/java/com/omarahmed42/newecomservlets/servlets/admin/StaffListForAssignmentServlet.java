package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.StaffServiceImpl;
import com.omarahmed42.newecomservlets.entities.StaffEntity;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StaffListForAssignmentServlet", value = "/admin/staff-list")
public class StaffListForAssignmentServlet extends HttpServlet {

    @EJB
    private StaffServiceImpl staffService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StaffEntity> allStaff = staffService.findAllStaff();
        request.setAttribute("allStaff", allStaff);
        request.getRequestDispatcher("/admin/staff/staff-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
