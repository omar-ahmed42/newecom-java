package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.StudentServiceImpl;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {

    @EJB
    private StudentServiceImpl studentService;

    @POST // Tested using postman
    public Response addStudent(StudentEntity student) {
        studentService.addStudent(student);
        return Response.status(201).build();
    }

    @GET
    @Path("/id={id}") // Tested using postman
    public Response getStudentById(@PathParam("id") Long id) {
        StudentEntity student = studentService.getStudentById(id);
        return Response.ok(student).build();
    }

    @PUT
    @Path("/id/{id}{firstName: (/firstName/[^/]+?)?}{lastName: (/lastName/[^/]+?)?}{dob: (/dob/[^/]+?)?}{academicYear: (/academicYear/[^/]+?)?}")
    // Tested using postman
    public Response updateStudent(@PathParam("id") Long id, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName,
                                  @PathParam("dob") String dateOfBirth, @PathParam("academicYear") String academicYear) {
        if (id == null) {
            return Response.status(400).build();
        }

        StudentEntity student = studentService.getStudentById(id);
        if (student == null) {
            return Response.status(404).build();
        }

        if (!isEmpty(firstName)) {
            student.setFirstName(firstName.split("/")[2]);
        }

        if (!isEmpty(lastName)) {
            student.setLastName(lastName.split("/")[2]);
        }

        if (!isEmpty(dateOfBirth)) {
//            student.setDateOfBirth(Date.valueOf(dateOfBirth.split("/")[2]));
            student.setDateOfBirth(LocalDate.parse(dateOfBirth.split("/")[2]));
        }

        if (!isEmpty(academicYear)) {
            student.setAcademicYear(AcademicYear.valueOf(academicYear.split("/")[2]));
        }

        studentService.updateStudent(student);
        return Response.ok().build();
    }

    @DELETE
    @Path("id={id}")
    public Response deleteStudent(@PathParam("id") Long id) { // Tested using postman
        if (id == null) {
            return Response.status(400).build();
        }

        StudentEntity student = studentService.getStudentById(id);
        if (student == null) {
            return Response.status(404).build();
        }

        studentService.deleteStudent(student);
        return Response.status(204).build();
    }

    @GET
    public Response getAllStudents() {
        List<StudentEntity> allStudents = studentService.findAllStudents();
        return Response.ok(allStudents).build();
    }

    @GET
    @Path("/list")
    public Response getSliceOfStudents(@QueryParam("page") int page, @QueryParam("size") int size) {
        if (page <= 0) {
            page = 1;
        }

        if (size <= 0) {
            size = 15;
        }

        List<StudentEntity> sliceOfStudents = studentService.findSliceOfStudents(page, size);
        return Response.ok(sliceOfStudents).build();
    }

    private boolean isEmpty(String text) {
        return text.equals("");
    }
}
