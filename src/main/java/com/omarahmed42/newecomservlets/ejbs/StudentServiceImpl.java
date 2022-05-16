package com.omarahmed42.newecomservlets.ejbs;

import com.omarahmed42.newecomservlets.dao.StudentDAO;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Stateless
@LocalBean
@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentDAO studentDAO;

    @Override
    public void addStudent(StudentEntity student) {
        studentDAO.addStudent(student);
    }

    @Override
    public void deleteStudent(StudentEntity student) {
        studentDAO.deleteStudent(student);
    }

    @Override
    public void updateStudent(StudentEntity student) {
        studentDAO.updateStudent(student);
    }

    @Override
    public StudentEntity getStudentById(Long id) {
        return studentDAO.findStudentById(id);
    }

    @Override
    public List<StudentEntity> findSliceOfStudents(int page, int size){
        return studentDAO.findSliceOfStudents(page, size);
    }

    @Override
    public List<StudentEntity> findAllStudents(){
        return studentDAO.findAllStudents();
    }


    @POST // Tested using postman
    public Response addStudentt(StudentEntity student){
        addStudent(student);
        return Response.status(201).build();
    }

    @GET
    @Path("/id={id}") // Tested using postman
    public Response getStudentByIdd(@PathParam("id") Long id){
        StudentEntity student = getStudentById(id);
        return Response.ok(student).build();
    }

    @PUT
    @Path("/id/{id}{firstName: (/firstName/[^/]+?)?}{lastName: (/lastName/[^/]+?)?}{dob: (/dob/[^/]+?)?}{academicYear: (/academicYear/[^/]+?)?}") // Tested using postman
    public Response updateStudentt(@PathParam("id") Long id, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName,
                                  @PathParam("dob") String dateOfBirth, @PathParam("academicYear") String academicYear){
        if (id == null){
            return Response.status(400).build();
        }

        StudentEntity student = getStudentById(id);
        if (student == null){
            return Response.status(404).build();
        }

        if (!isEmpty(firstName)){
            student.setFirstName(firstName.split("/")[2]);
        }

        if (!isEmpty(lastName)){
            student.setLastName(lastName.split("/")[2]);
        }

        if (!isEmpty(dateOfBirth)){
//            student.setDateOfBirth(Date.valueOf(dateOfBirth.split("/")[2]));
            student.setDateOfBirth(LocalDate.parse(dateOfBirth.split("/")[2]));
        }

        if (!isEmpty(academicYear)){
            student.setAcademicYear(AcademicYear.valueOf(academicYear.split("/")[2]));
        }

        updateStudent(student);
        return Response.ok().build();
    }

    @DELETE
    @Path("id={id}")
    public Response deleteStudentt(@PathParam("id") Long id){ // Tested using postman
        if (id == null){
            return Response.status(400).build();
        }

        StudentEntity student = getStudentById(id);
        if (student == null){
            return Response.status(404).build();
        }

        deleteStudent(student);
        return Response.status(204).build();
    }

    @GET
    public Response getAllStudentss(){
        List<StudentEntity> allStudents = findAllStudents();
        return Response.ok(allStudents).build();
    }

    @GET
    @Path("/list")
    public Response getSliceOfStudentss(@QueryParam("page") int page, @QueryParam("size") int size){
        if (page <= 0){
            page = 1;
        }

        if (size <= 0){
            size = 15;
        }

        List<StudentEntity> sliceOfStudents = findSliceOfStudents(page, size);
        return Response.ok(sliceOfStudents).build();
    }

    private boolean isEmpty(String text){
        return text.equals("");
    }

}
