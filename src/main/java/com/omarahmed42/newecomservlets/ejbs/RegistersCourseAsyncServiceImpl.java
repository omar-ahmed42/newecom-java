package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.RegistersCourseDao;
import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/RegistersCourse"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class RegistersCourseAsyncServiceImpl implements RegistersCourseAsyncService, MessageListener {

    @Inject
    private RegistersCourseDao registersCourseDao;

    @Override
    public void registersInACourse(RegistersCourseEntity registersCourse) {
        registersCourse.getCourseByCourseCode()
                .getPrerequisitesByCourseCode()
                .forEach(prerequisite -> {
                    if (!isFulfilledPrerequisites(prerequisite,
                        registersCourse
                        .getStudentByStudentId()
                        .getAcademicYear())){
                        // Todo create a dedicated exception for it instead of a generic one
                        throw new RuntimeException("A prerequisite has not been fulfilled");
                    }
    });
        registersCourseDao.addRegistersCourse(registersCourse);
    }

    private boolean isFulfilledPrerequisites(PrerequisiteEntity prerequisite, AcademicYear currentAcademicYear){
        return prerequisite.getAcademicYear().getValue() <= currentAcademicYear.getValue();
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            RegistersCourseEntity registersCourse = (RegistersCourseEntity) objectMessage.getObject();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
