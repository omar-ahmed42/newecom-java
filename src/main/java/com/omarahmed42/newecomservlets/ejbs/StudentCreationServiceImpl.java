package com.omarahmed42.newecomservlets.ejbs;

import com.omarahmed42.newecomservlets.dao.StudentDAO;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.structures.Range;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Stateful
@LocalBean
@StatefulTimeout(unit = TimeUnit.HOURS, value = 6)
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
            System.out.println("In: " + (max - min));
            return max - min;
        }

        if (min < minAndMaxIdsInRange.min) {
            System.out.println("Calc_MinRange: " + minAndMaxIdsInRange.min);
            System.out.println("Calc_Min: " + minAndMaxIdsInRange.min);
            numberOfVacantPlaces = numberOfVacantPlaces + (minAndMaxIdsInRange.min - min);
        }

        if (max > minAndMaxIdsInRange.max) {
            System.out.println("Calc_MaxRange: " + minAndMaxIdsInRange.max);
            System.out.println("Calc_Max: " + minAndMaxIdsInRange.max);
            numberOfVacantPlaces = numberOfVacantPlaces + (max - minAndMaxIdsInRange.max);
        }

        System.out.println("CALC_OUT_MAX_RANGE: " + minAndMaxIdsInRange.max);
        System.out.println("CALC_OUT_MIN_RANGE: " + minAndMaxIdsInRange.min);
        return numberOfVacantPlaces;
    }

    @Override
    public void addStudent(StudentEntity student) {
        Range minAndMaxIdsInRange = studentDAO.findMinAndMaxIdsInRange(min, max);
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
            } else if (min < minAndMaxIdsInRange.min) {
                student.setStudentId(minAndMaxIdsInRange.min - 1);
            } else if (max > minAndMaxIdsInRange.max) {
                student.setStudentId(minAndMaxIdsInRange.max + 1);
            }

            studentDAO.addStudent(student);
        }
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }
}
