package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.AdministratorEntity;

public interface AdministratorDao {
    AdministratorEntity findAdministratorById(Long id);
    void addAdministrator(AdministratorEntity administratorEntity);
    void deleteAdministrator(AdministratorEntity administratorEntity);
    void updateAdministrator(AdministratorEntity administratorEntity);
    AdministratorEntity findAdministratorByCredentials(Long id, String password);

}
