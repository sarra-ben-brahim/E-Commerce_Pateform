package com.coding.dojo.Ecommerce.services.interf;

import com.coding.dojo.Ecommerce.dto.LoginRequest;
import com.coding.dojo.Ecommerce.dto.Response;
import com.coding.dojo.Ecommerce.dto.UserDto;
import com.coding.dojo.Ecommerce.entities.User;

public interface UserService {
    Response registerUser(UserDto registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    User getLoginUser();
    Response getUserInfoAndOrderHistory();
}