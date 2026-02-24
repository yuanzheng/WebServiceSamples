package com.ytechtrade.onlineshoppingsystem.service;

import com.ytechtrade.onlineshoppingsystem.model.User;
import com.ytechtrade.onlineshoppingsystem.payload.AddressDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {

    AddressDTO createAddress(@Valid AddressDTO addressDTO, User user);

    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressById(Long addressId);

    List<AddressDTO> getUserAddresses(User user);

    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}
