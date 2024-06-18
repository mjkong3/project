package com.example.pet.mapper;

import com.example.pet.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper {

    int insertCust(CustomerDto customerDto);

    CustomerDto overlapId(String id);

    CustomerDto overlapTel(String tel);

    CustomerDto loginCustomer(String id);

    int loginId(String id);

    CustomerDto longinId(String id);

    void updateCustomerTotalPrice(@Param("custNum") Long custNum);



}