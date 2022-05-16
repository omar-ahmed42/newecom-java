package com.omarahmed42.newecomservlets.ejbs;

import com.omarahmed42.newecomservlets.dao.StudentDAO;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.structures.Range;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Stateful
@LocalBean
@StatefulTimeout(unit = TimeUnit.HOURS, value = 6)
@Path("/bean/studentCreation")
public class StudentCreationServiceImpl implements StudentCreationService {

    private Long min;
    private Long max;

    @Inject
    private StudentDAO studentDAO;

    @Override
    public void setRange(Long min, Long max) {
        this.min = min;
        this.max = max;
    }

    public Long calculateNumberOfVacantPlaces() {
        Range minAndMaxIdsInRange = studentDAO.findMinAndMaxIdsInRange(min, max);
        Long numberOfVacantPlaces = Long.valueOf(0);

        if (Objects.isNull(minAndMaxIdsInRange.min) || Objects.isNull(minAndMaxIdsInRange.max)) {
            return max - min;
        }

        if (min < minAndMaxIdsInRange.min) {
            numberOfVacantPlaces = numberOfVacantPlaces + (minAndMaxIdsInRange.min - min);
        }

        if (max > minAndMaxIdsInRange.max) {
            numberOfVacantPlaces = numberOfVacantPlaces + (max - minAndMaxIdsInRange.max);
        }

        return numberOfVacantPlaces;
    }

    public Range findMinAndMaxIdsOrNextInRange(){
        return studentDAO.findMinAndMaxIdsOrNextInRange(min, max);
    }

    @Override
    @POST
    public void addStudent(StudentEntity student) {
        Range minAndMaxIdsInRange = studentDAO.findMinAndMaxIdsOrNextInRange(min, max);
        Long numberOfVacantPlaces = Long.valueOf(0);


        if (Objects.isNull(minAndMaxIdsInRange.min) || Objects.isNull(minAndMaxIdsInRange.max)) {
            numberOfVacantPlaces += (max - min);
        }

        if (Objects.nonNull(minAndMaxIdsInRange.min) && min < minAndMaxIdsInRange.min) {
            numberOfVacantPlaces = numberOfVacantPlaces + (minAndMaxIdsInRange.min - min);
        }

        if (Objects.nonNull(minAndMaxIdsInRange.max) && max > minAndMaxIdsInRange.max) {
            numberOfVacantPlaces = numberOfVacantPlaces + (max - minAndMaxIdsInRange.max);
        }

        if (numberOfVacantPlaces > 0) {

            if (Objects.isNull(minAndMaxIdsInRange.min) || Objects.isNull(minAndMaxIdsInRange.max)) {
                student.setStudentId(min);
            } else if (minAndMaxIdsInRange.isMinNextID) {
                student.setStudentId(minAndMaxIdsInRange.min);
            }
            else if (min < minAndMaxIdsInRange.min) {
                student.setStudentId(minAndMaxIdsInRange.min - 1);
            } else if (max > minAndMaxIdsInRange.max) {
                student.setStudentId(minAndMaxIdsInRange.max + 1);
            }

            studentDAO.addStudent(student);
        }
    }

    public Long countNumberOfIdsInRange(){
        return studentDAO.countNumberOfIdsInRange(min, max);
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }
}
