package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.AdministratorEntity;

public interface AdministratorService {
    void addAdministrator(AdministratorEntity administrator);
    void deleteAdministrator(AdministratorEntity administrator);
    void updateAdministrator(AdministratorEntity administrator);
    AdministratorEntity getAdministratorById(Long id);

}
