package com.example.pet.service;

import com.example.pet.constant.OrderStatus;
import com.example.pet.dto.*;
import com.example.pet.exception.OutOfStockException;
import com.example.pet.form.OrderForm;
import com.example.pet.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private final CartService cartService;
    private final ItemService itemService;

    //order 테이블에 추가
    public OrderDto insertOrder(OrderForm orderForm, String id){
        System.out.println("orderService 실행");

        OrderDto orderDto = new OrderDto();

        CustomerDto customerDto = customerService.loginId(id);

        orderDto.setCustNum(customerDto.getCustNum());

        if (orderForm.getOrderReq() == null || orderForm.getOrderReq().trim().isEmpty()) {
            orderDto.setOrderReq("조심히 천천히 와주세요");
        }

        orderDto.setOrderReq(orderForm.getOrderReq());
        orderDto.setAddress(orderForm.getAddress());

        System.out.println(OrderStatus.ORDER);
        System.out.println("orderDto : " + orderDto);

        orderMapper.insertOrder(orderDto);

        return orderDto;

    }

    //order_detail 테이블에 추가
    public List<OrderDetailDto> insertDetail(OrderForm orderForm,OrderDto orderDto){
        List<OrderDetailDto> list = new ArrayList<>();

        for (Long cartItemNum : orderForm.getCartItemNum()) {
            CartDetailDto cartDetailDto = cartService.selectItem(cartItemNum);

            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setOrderNum(orderDto.getOrderNum());
            orderDetailDto.setItemNum(cartDetailDto.getItemNum());
            orderDetailDto.setItemCnt(cartDetailDto.getCount());
            orderDetailDto.setOrderStatus(OrderStatus.ORDER);


            orderDetailDto.setPrice(cartDetailDto.getItemPrice() * cartDetailDto.getCount());

            // 주문하면 item 테이블에 재고가 감소해야 함
            ItemDto itemDto = itemService.selectItem(cartDetailDto.getItemNum());
            itemDto.setItemCnt(itemDto.getItemCnt() - cartDetailDto.getCount());
            int cnt = itemDto.getItemCnt();
            String itemName = itemDto.getItemName();

            if (cnt < 0) {
                throw new OutOfStockException("'" + itemName + "' 의 재고가 부족합니다 : " + -cnt + "개 부족");
            } else {
                orderMapper.insertDetail(orderDetailDto);
                list.add(orderDetailDto);

                System.out.println(orderDetailDto);
            }

            itemService.updateCnt(itemDto);


            // 주문하면 장바구니에서 해당 주문한 제품 정보 삭제
            cartService.deleteCartItem(cartItemNum);

        }
        return list;
    }

    public List<OrderDetailDto> customerOrder(Long custNum){
        List<OrderDetailDto> list = orderMapper.customerOrder(custNum);
        return list;
    };


}
