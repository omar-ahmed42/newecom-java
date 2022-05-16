package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.AdministratorEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class AdministratorDaoImpl implements AdministratorDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AdministratorEntity findAdministratorById(Long id) {
        return entityManager.find(AdministratorEntity.class, id);
    }

    @Override
    public void addAdministrator(AdministratorEntity administratorEntity) {
        entityManager.persist(administratorEntity);
    }

    @Override
    public void deleteAdministrator(AdministratorEntity administratorEntity) {
        entityManager.remove(entityManager.contains(administratorEntity) ? administratorEntity : entityManager.merge(administratorEntity));
    }

    @Override
    public void updateAdministrator(AdministratorEntity administratorEntity) {
        entityManager.merge(administratorEntity);
        entityManager.flush();
    }

    @Override
    public AdministratorEntity findAdministratorByCredentials(Long id, String password) {
        TypedQuery<AdministratorEntity> administratorEntityTypedQuery = entityManager.createQuery("SELECT admin FROM AdministratorEntity admin WHERE admin.administratorId = :id AND admin.password = :password", AdministratorEntity.class);
        administratorEntityTypedQuery.setParameter("id", id);
        administratorEntityTypedQuery.setParameter("password", password);
        return administratorEntityTypedQuery.getSingleResult();
    }
}
