package com.example.pet.service;

import com.example.pet.dto.CustomerDto;
import com.example.pet.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public int insertCust(CustomerDto customerDto) {
        overlapId(customerDto.getCustId());
        overlapTel(customerDto.getCustTel());
        return customerMapper.insertCust(customerDto);
    }

    public CustomerDto loginCustomer(String id) {
        return customerMapper.loginCustomer(id);
    }

    public CustomerDto overlapId(String id) {
        return customerMapper.overlapId(id);
    }

    public CustomerDto overlapTel(String tel) {
        return customerMapper.overlapTel(tel);
    }

    public CustomerDto loginId(String id){
        return customerMapper.longinId(id);
    }


    public void updateCustomerTotalPrice(Long custNum) {
        customerMapper.updateCustomerTotalPrice(custNum);
    }

}
