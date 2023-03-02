package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.dto.CustomerDTO;
import com.alpha.hotel.hotelbookingbackend.entity.Customer;
import com.alpha.hotel.hotelbookingbackend.repo.CustomerRepo;
import com.alpha.hotel.hotelbookingbackend.service.CustomerService;
import com.alpha.hotel.hotelbookingbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        if(customerRepo.existsByEmail(customerDTO.getEmail())){
            return VarList.RSP_DUPLICATED;
        }else{
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            customer.setDateOfBirth(LocalDateTime.parse(customerDTO.getDateOfBirth(), formatter));
            customerRepo.save(customer);
            return VarList.RSP_SUCCESS;
        }
    }
}
