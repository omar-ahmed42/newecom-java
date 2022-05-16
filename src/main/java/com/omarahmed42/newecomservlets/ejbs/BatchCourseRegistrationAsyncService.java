package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;

import java.util.Set;

public interface BatchCourseRegistrationAsyncService {
    void registersInCourses(Set<RegistersCourseEntity> registersCourses);
}

