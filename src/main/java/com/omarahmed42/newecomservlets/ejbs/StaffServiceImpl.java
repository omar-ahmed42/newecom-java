package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.StaffDao;
import com.omarahmed42.newecomservlets.entities.StaffEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class StaffServiceImpl implements StaffService{
    @Inject
    private StaffDao staffDao;

    @Override
    public void addStaff(StaffEntity staff) {
        staffDao.addStaff(staff);
    }

    @Override
    public void deleteStaff(StaffEntity staff) {
        staffDao.deleteStaff(staff);
    }

    @Override
    public void updateStaff(StaffEntity staff) {
        staffDao.updateStaff(staff);
    }

    @Override
    public StaffEntity getStaffById(Long id) {
        return staffDao.findStaffById(id);
    }

    @Override
    public List<StaffEntity> findAllStaff(){
        return staffDao.findAllStaff();
    }
}
