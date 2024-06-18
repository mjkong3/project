package com.example.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminOrderListDto {
    private Integer orderNum;
    private String itemName;
    private String custName;
    private String custId;
    private LocalDateTime orderDate;
    private Integer price;
    private Integer itemCnt;
    private String orderReq;
    private String address;
    private String orderStatus;




}
