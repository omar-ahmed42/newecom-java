package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.PrerequisiteDao;
import com.omarahmed42.newecomservlets.dao.RegistersCourseDao;
import com.omarahmed42.newecomservlets.dao.StudentDAO;
import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.List;
import java.util.Set;

@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BatchRegistersCourse"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BatchCourseRegistrationAsyncServiceImpl implements BatchCourseRegistrationAsyncService, MessageListener {

    @Inject
    private RegistersCourseDao registersCourseDao;

    @Inject
    private PrerequisiteDao prerequisiteDao;

    @Inject
    private StudentDAO studentDAO;

    @Override
    public void registersInCourses(Set<RegistersCourseEntity> registersCourses) {
        //TODO: Enable batch persistence
        try {
            registersCourses.forEach(this::accept);
        } catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
        }
    }

    private boolean isFulfilledPrerequisites(PrerequisiteEntity prerequisite, AcademicYear currentAcademicYear){
        return prerequisite.getAcademicYear().getValue() <= currentAcademicYear.getValue();
    }

    private void accept(RegistersCourseEntity registersCourseEntity) {
        List<PrerequisiteEntity> prerequisites = prerequisiteDao.getPrerequisitesByCourseCode(registersCourseEntity.getCourseCode());
        prerequisites.forEach(prerequisite -> {
            boolean fulfilledPrerequisites = isFulfilledPrerequisites(prerequisite,
                    studentDAO
                            .findStudentById(registersCourseEntity.getStudentId())
                            .getAcademicYear());
            if (!fulfilledPrerequisites) {
                throw new RuntimeException("Prerequisites for " + prerequisite.getCourseCode() + " has not been fulfilled");
            }
        });
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Set<RegistersCourseEntity> registersCourses = (Set<RegistersCourseEntity>) objectMessage.getObject();
            registersInCourses(registersCourses);
            registersCourseDao.addRegistersCourses(registersCourses);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
