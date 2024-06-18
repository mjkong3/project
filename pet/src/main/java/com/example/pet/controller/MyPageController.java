package com.example.pet.controller;

import com.example.pet.dto.CustomerDto;
import com.example.pet.dto.MyPageDto;
import com.example.pet.service.CustomerService;
import com.example.pet.service.MyPageService;
import com.example.pet.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("/myPage")
    public String myPage(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        CustomerDto customerDto = customerService.loginCustomer(principal.getName());

        List<MyPageDto> list = myPageService.selectMyPage(customerDto.getCustNum());

        model.addAttribute("list", list);
        model.addAttribute("customer", customerDto);

        return "/myPage.html";
    }

    @DeleteMapping("/order/cancel/{orderDetailNum}")
    public ResponseEntity deleteOrderDetail(@PathVariable("orderDetailNum") Long orderDetailNum){

        Map<String, Object> map = new HashMap<>();

        map.put("orderStatus", "CANCEL");
        map.put("orderDetailNum", orderDetailNum);

        myPageService.changeStatus(map);
        return new ResponseEntity<Long>(orderDetailNum, HttpStatus.OK);
    }
}
