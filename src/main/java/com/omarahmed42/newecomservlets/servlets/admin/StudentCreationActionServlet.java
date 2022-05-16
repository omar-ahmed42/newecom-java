package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.StudentCreationServiceImpl;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "StudentCreationActionServlet", value = "/admin/student-creation-action")
public class StudentCreationActionServlet extends HttpServlet {

    @EJB
    private StudentCreationServiceImpl studentCreationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/student/creation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long min = null;
        Long max = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("range.min")) {
                min = Long.valueOf(cookie.getValue());
            } else if (cookie.getName().equals("range.max")) {
                max = Long.valueOf(cookie.getValue());
            }
        }

        System.out.println("MIN_ACTION: " + min);
        System.out.println("MAX_ACTION: " + max);
        try {
            studentCreationService.setRange(min, max);
            StudentEntity student = createUserUsingFormData(request);
            studentCreationService.addStudent(student);
            request.setAttribute("success", true);
            doGet(request, response);
        } catch (Exception e) {
            request.setAttribute("failure", true);
            e.printStackTrace();
            doGet(request, response);
        }

    }

    private StudentEntity createUserUsingFormData(HttpServletRequest request) {
        String firstName = request.getParameter("first-name-input");
        String lastName = request.getParameter("last-name-input");
        String dateParameter = request.getParameter("dob");
        LocalDate dateOfBirth = LocalDate.parse(dateParameter);
        AcademicYear academicYear = AcademicYear.valueOf(request.getParameter("academic-year"));

        return new StudentEntity(firstName, lastName, dateOfBirth, academicYear);
    }
}
