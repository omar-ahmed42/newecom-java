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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long min = Long.valueOf(request.getParameter("range-min"));
        Long max = Long.valueOf(request.getParameter("range-max"));
        studentCreationService.setRange(min, max);
        Long numberOfVacantPlaces = studentCreationService.calculateNumberOfVacantPlaces();
        Long count = studentCreationService.countNumberOfIdsInRange();
        if (numberOfVacantPlaces <= 0 || (max - min + 1) <= count){
            response.sendRedirect("/newecom-servlets-1.0-SNAPSHOT/admin/student/student-range.jsp");
            return;
        }

        Range range = new Range(min, max);
        request.setAttribute("range", range);
        request.getRequestDispatcher("student-creation").forward(request, response);
    }
}
