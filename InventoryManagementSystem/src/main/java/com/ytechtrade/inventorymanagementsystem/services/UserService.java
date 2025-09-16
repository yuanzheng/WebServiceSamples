package com.ytechtrade.inventorymanagementsystem.services;

import com.ytechtrade.inventorymanagementsystem.models.User;
import com.ytechtrade.inventorymanagementsystem.models.dtos.LoginRequest;
import com.ytechtrade.inventorymanagementsystem.models.dtos.RegisterRequest;
import com.ytechtrade.inventorymanagementsystem.models.dtos.Response;
import com.ytechtrade.inventorymanagementsystem.models.dtos.UserDTO;

public interface UserService {
    Response registerUser(RegisterRequest registerRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getCurrentLoggedInUser();

    Response getUserById(Long id);

    Response updateUser(Long id, UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);
}
