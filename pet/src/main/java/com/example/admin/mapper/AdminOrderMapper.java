package com.example.admin.mapper;


import com.example.admin.dto.AdminOrderListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminOrderMapper {

    List<AdminOrderListDto> orderListAll();

    List<AdminOrderListDto> searchOrders(@Param("searchType") String searchType,
                                    @Param("searchKeyword") String searchKeyword,
                                    @Param("startDate") String startDate,
                                    @Param("endDate") String endDate,
                                    @Param("orderStatus") String orderStatus);
}
