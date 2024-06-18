package com.example.pet.controller;

import com.example.pet.dto.ItemDto;
import com.example.pet.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HtmlController {

    private final ItemService itemService;

    @GetMapping("/")
    public String petForm(Model model){

        List<ItemDto> items = itemService.itemAll();
        model.addAttribute("items",items);

        return "index";
    }

    @GetMapping("/login.html")
    public String loginForm(){
        return "login";
    }

/*    @GetMapping("/shoppingbasket")
    public String shopping(){
        return "shoppingbasket";
    }*/

//    @GetMapping("review")
//    public String review(){
//        return "review";
//    }

//    @GetMapping("notice")
//    public String notice(){
//        return "notice";
//    }

    @GetMapping("/login_proc")
    public String login(){
        return "/login";
    }

    @GetMapping("/index")
    public String mainForm() {
        return "index";  // templates/index.html을 가리킵니다.
    }

    //상품 카테고리에 따른 페이지
    @GetMapping("/petfood.html")
    public String petFood(Model model){
        String categoryName = "사료";

        List<ItemDto> food = itemService.selectCate(categoryName);
        model.addAttribute("food", food);

        return "petfood";
    }

    @GetMapping("/pethealth.html")
    public String petHealth(Model model){
        String categoryName = "건강,미용";

        List<ItemDto> food = itemService.selectCate(categoryName);
        model.addAttribute("food", food);

        return "pethealth";
    }

    @GetMapping("/petvitamin.html")
    public String petVitamin(Model model){
        String categoryName = "영양제";

        List<ItemDto> food = itemService.selectCate(categoryName);
        model.addAttribute("food", food);

        return "petvitamin";
    }

    @GetMapping("/petplay.html")
    public String petPlay(Model model){
        String categoryName = "외출,놀이,훈련";

        List<ItemDto> food = itemService.selectCate(categoryName);
        model.addAttribute("food", food);

        return "petplay";
    }


}
