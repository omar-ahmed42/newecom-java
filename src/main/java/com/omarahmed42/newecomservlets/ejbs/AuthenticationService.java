package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.enums.UserType;

import javax.ejb.Local;

@Local
public interface AuthenticationService {
    Object login(String id, String password, UserType userType);
}
