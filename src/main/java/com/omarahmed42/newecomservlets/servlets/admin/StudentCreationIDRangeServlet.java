package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.StudentCreationServiceImpl;
import com.omarahmed42.newecomservlets.structures.Range;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentCreationIDRangeServlet", value = "/admin/student-range")
public class StudentCreationIDRangeServlet extends HttpServlet {

    @EJB
    private StudentCreationServiceImpl studentCreationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/student/student-range.jsp").forward(request, response);
//        response.sendRedirect("/admin/student/student-range.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long min = Long.valueOf(request.getParameter("range-min"));
        Long max = Long.valueOf(request.getParameter("range-max"));
        studentCreationService.setRange(min, max);
        Long numberOfVacantPlaces = studentCreationService.calculateNumberOfVacantPlaces();
        System.out.println("Number Of Vacant_Servlet: " + numberOfVacantPlaces);
        if (numberOfVacantPlaces <= 0){
            response.sendRedirect("/newecom-servlets-1.0-SNAPSHOT/admin/student/student-range.jsp");
            return;
        }

        Range range = new Range(min, max);
        request.setAttribute("range", range);
        System.out.println("here");
//        request.getRequestDispatcher("/admin/student/creation.jsp").forward(request, response);
        request.getRequestDispatcher("student-creation").forward(request, response);
//        getServletContext().getRequestDispatcher("/newecom-servlets-1.0-SNAPSHOT/admin/student-creation").forward(request, response);
//        response.sendRedirect("student-creation");
    }
}
