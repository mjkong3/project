package com.example.admin.service;


import com.example.admin.dto.AdminOrderListDto;
import com.example.admin.mapper.AdminOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOrderService {

    @Autowired
    private AdminOrderMapper orderMapper;

    public List<AdminOrderListDto> orderListAll() {
        return orderMapper.orderListAll();
    }

    public List<AdminOrderListDto> getOrders(String searchType, String searchKeyword, String startDate, String endDate, String orderStatus) {
        return orderMapper.searchOrders(searchType, searchKeyword, startDate, endDate, orderStatus);
    }
}
