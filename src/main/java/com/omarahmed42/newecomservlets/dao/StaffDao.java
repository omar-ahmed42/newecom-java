package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.StaffEntity;

import java.util.List;

public interface StaffDao {
    StaffEntity findStaffById(Long id);
    void addStaff(StaffEntity staffEntity);
    void deleteStaff(StaffEntity staffEntity);
    void updateStaff(StaffEntity staffEntity);
    StaffEntity findStaffByCredentials(Long id, String password);
    List<StaffEntity> findAllStaff();
}
