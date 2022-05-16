package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.StudentCreationServiceImpl;
import com.omarahmed42.newecomservlets.structures.Range;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentCreationServlet", value = "/admin/student-creation")
public class StudentCreationServlet extends HttpServlet {

    @EJB
    private StudentCreationServiceImpl studentCreationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Range range = (Range) request.getAttribute("range");
        studentCreationService.setRange(range.min, range.max);
        Long numberOfVacantPlaces = studentCreationService.calculateNumberOfVacantPlaces();

        if (numberOfVacantPlaces <= 0) {
            response.sendRedirect("/newecom-servlets-1.0-SNAPSHOT/admin/student/student-range.jsp");
            return;
        }

        Cookie rangeMin = new Cookie("range.min", range.min.toString());
        rangeMin.setMaxAge(5 * 1000 * 10);
        response.addCookie(rangeMin);

        Cookie rangeMax = new Cookie("range.max", range.max.toString());
        rangeMin.setMaxAge(5 * 1000 * 10);
        response.addCookie(rangeMax);

        request.getRequestDispatcher("/admin/student/creation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Range range = (Range) request.getAttribute("range");
        doGet(request, response);
    }
}
