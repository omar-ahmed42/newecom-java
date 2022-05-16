package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Set;

@Path("/course/registration/batch")
public class BatchCourseRegistrationAsyncController {

    @Resource(lookup = "java:/jms/BatchRegistersCourse")
    private Queue queue;

    @Resource(lookup = "java:jboss/DefaultJMSConnectionFactory")
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registersInCourses(Set<RegistersCourseEntity> registersCourses){
        System.out.println("hello");
        JMSContext context = connectionFactory.createContext();
        context.createProducer().send(queue, (Serializable) registersCourses);
        context.close();
        return Response.ok().build();
    }
}
