package com.example.admin.controller;


import com.example.admin.dto.AdminCategoryDto;
import com.example.admin.dto.AdminCustomerDto;
import com.example.admin.dto.AdminOrderListDto;
import com.example.admin.service.AdminCategoryService;
import com.example.admin.service.AdminCustomerService;
import com.example.admin.service.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminCategoryService categoryService;

    @Autowired
    private AdminCustomerService customerService;

    @Autowired
    private AdminOrderService orderService;

    //메인 관리자페이지
    @GetMapping("/admin")
    public String adminAdminForm() {
        return "admin/admin";
    }

    //카테고리 전체 조회
    @GetMapping("/manageCategory")
    public String manageCategory(Model model) {
        List<AdminCategoryDto> category = categoryService.allCategory();
        model.addAttribute("category", category);

        return "admin/manageCategory";
    }

    //카테고리 추가하기
    @PostMapping("/manageCategory")
    public String addCategory(@RequestParam("categoryName") String categoryName, Model model) {
        try {
            categoryService.inCategory(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "카테고리 등록 중에 에러 발생");
            return "admin/manageCategory";

        }
        return "redirect:/manageCategory";

    }

    // 카테고리 삭제
    @PostMapping("/deleteCategory")
    @ResponseBody
    public ResponseEntity<String> deleteCategory(@RequestParam("categoryName") String categoryName) {
        try {
            categoryService.deleteCategory(categoryName);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }

    //회원관리 검색 + 전체 조회
    @GetMapping("/manageCustomer")
    public String searchAllCust(@RequestParam(value = "searchField", required = false) String searchField,
                                @RequestParam(value = "searchValue", required = false) String searchValue,
                                @RequestParam(value = "gradeName", required = false) String gradeName,
                                Model model) {

        List<AdminCustomerDto> cust = customerService.searchCustomer(searchField, searchValue, gradeName);

        model.addAttribute("cust", cust);
        return "admin/manageCustomer";
    }

    // 유저 삭제
    @PostMapping("/deleteCustomer")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@RequestParam("custNum") int custNum) {
        try {
            customerService.deleteCust(custNum);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }


//    //전체 주문 리스트
//    @GetMapping("/manageOrder")
//    public String orderListAllView(Model model) {
//
//        List<OrderListDto> order = orderService.orderListAll();
//        model.addAttribute("orders", order);
//
//        return "admin/manageOrder";
//    }

    //주문 검색
    @GetMapping("/manageOrder")
    public String orderListAllView(
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "orderStatus", required = false) String orderStatus,
            Model model) {

        List<AdminOrderListDto> orders = orderService.getOrders(searchType, searchKeyword, startDate, endDate, orderStatus);
        model.addAttribute("orders", orders);

        return "admin/manageOrder";
    }



}
