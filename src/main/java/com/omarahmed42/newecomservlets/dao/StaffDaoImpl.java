package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.StaffEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class StaffDaoImpl implements StaffDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StaffEntity findStaffById(Long id) {
        return entityManager.find(StaffEntity.class, id);
    }

    @Override
    public void addStaff(StaffEntity staffEntity) {
        entityManager.persist(staffEntity);
        entityManager.flush();
    }

    @Override
    public void deleteStaff(StaffEntity staffEntity) {
        entityManager.remove(entityManager.contains(staffEntity) ? staffEntity : entityManager.merge(staffEntity));
    }

    @Override
    public void updateStaff(StaffEntity staffEntity) {
        entityManager.merge(staffEntity);
    }

    @Override
    public StaffEntity findStaffByCredentials(Long id, String password) {
        TypedQuery<StaffEntity> staffEntityTypedQuery = entityManager.createQuery("SELECT staff FROM StaffEntity staff WHERE staff.staffId = :id AND staff.password = :password", StaffEntity.class);
        staffEntityTypedQuery.setParameter("id", id);
        staffEntityTypedQuery.setParameter("password", password);
        return staffEntityTypedQuery.getSingleResult();
    }

    @Override
    public List<StaffEntity> findAllStaff(){
        return entityManager.createQuery("SELECT s FROM StaffEntity s", StaffEntity.class).getResultList();
    }

}
