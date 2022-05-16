package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.StaffEntity;

public interface StaffService {
    void addStaff(StaffEntity staff);
    void deleteStaff(StaffEntity staff);
    void updateStaff(StaffEntity staff);
    StaffEntity getStaffById(Long id);
}
