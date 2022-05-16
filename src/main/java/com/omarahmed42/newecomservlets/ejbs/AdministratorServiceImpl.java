package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.AdministratorDaoImpl;
import com.omarahmed42.newecomservlets.entities.AdministratorEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class AdministratorServiceImpl implements AdministratorService{

    @Inject
    private AdministratorDaoImpl administratorDao;

    public void addAdministrator(AdministratorEntity administrator) {
        administratorDao.addAdministrator(administrator);
    }

    public void deleteAdministrator(AdministratorEntity administrator) {
        administratorDao.deleteAdministrator(administrator);
    }

    public void updateAdministrator(AdministratorEntity administrator) {
        administratorDao.updateAdministrator(administrator);
    }

    public AdministratorEntity getAdministratorById(Long id) {
        return administratorDao.findAdministratorById(id);
    }
}
