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
        System.out.println("Get_min: " + range.min);
        System.out.println("Get_max: " + range.max);
        studentCreationService.setRange(range.min, range.max);
        Long numberOfVacantPlaces = studentCreationService.calculateNumberOfVacantPlaces();
        System.out.println("MIN: " + studentCreationService.getMin());
        System.out.println("MAX: " + studentCreationService.getMax());

        if (numberOfVacantPlaces <= 0) {
            response.sendRedirect("/newecom-servlets-1.0-SNAPSHOT/admin/student/student-range.jsp");
            return;
        }

        Cookie rangeMin = new Cookie("range.min", range.min.toString());
        rangeMin.setMaxAge(5 * 1000 * 10);
        response.addCookie(rangeMin);
        System.out.println("name in creation: "+  rangeMin.getName());

        Cookie rangeMax = new Cookie("range.max", range.max.toString());
        rangeMin.setMaxAge(5 * 1000 * 10);
        response.addCookie(rangeMax);

        response.addCookie(new Cookie("bateekha", "bateekhaValue"));
        request.getRequestDispatcher("/admin/student/creation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Range range = (Range) request.getAttribute("range");
        System.out.println("Range_min: " + range.min);
        System.out.println("Range_max: " + range.max);
        doGet(request, response);
    }
}
