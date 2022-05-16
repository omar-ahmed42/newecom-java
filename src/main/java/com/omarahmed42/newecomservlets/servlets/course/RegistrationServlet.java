package com.omarahmed42.newecomservlets.servlets.course;

import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "RegistrationServlet", value = "/course/registration")
public class RegistrationServlet extends HttpServlet {

    @Resource(lookup = "java:/jms/BatchRegistersCourse")
    private Queue queue;

    @Resource(lookup = "java:jboss/DefaultJMSConnectionFactory")
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/course/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long studentId = (Long) request.getSession().getAttribute("studentId"); //TODO: Handle it
        String selectedCourses[] = request.getParameterValues("eligible-courses");
        JMSContext context = connectionFactory.createContext();
        Set<RegistersCourseEntity> registersCourses = new HashSet<>();
        for (String courseCode : selectedCourses){
            registersCourses.add(new RegistersCourseEntity(studentId, courseCode));
        }
        context.createProducer().send(queue, (Serializable) registersCourses);
        context.close();
        request.getRequestDispatcher("/all-courses").forward(request, response);
    }
}
