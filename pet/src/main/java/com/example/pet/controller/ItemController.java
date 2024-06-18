package com.example.pet.controller;

import com.example.pet.dto.ItemDto;
import com.example.pet.service.CustomerService;
import com.example.pet.service.FileService;
import com.example.pet.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {


    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemService itemService;
    private final FileService fileService;
    private final CustomerService customerService;

//    @GetMapping("/addProductAdd.html")
//    public String addProduct(Model model){
//        model.addAttribute("product",new ItemForm());
//        return "addProductAdd";
//    }

//    @PostMapping("/addProduct")
//    public String addProducts(@Valid ItemForm itemForm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("errorMessage", "상품 등록 필드를 모두 입력해야합니다!");
//            model.addAttribute("product", itemForm);
//            return "addProductAdd";
//        }
//
//        try {
//            ItemDto itemDto = new ItemDto();
//            itemDto.setCategoryName(itemForm.getCategoryName());
//            itemDto.setItemName(itemForm.getItemName());
//            itemDto.setItemPrice(itemForm.getPrice());
//            itemDto.setItemCnt(itemForm.getCount());
//            itemDto.setItemInfo(itemForm.getItemDetail());
//
//            MultipartFile itemImgFile = itemForm.getItemImgFile();
//
//            if (!itemImgFile.isEmpty()) {
//                String originalFilename = itemImgFile.getOriginalFilename();
//                String savedFilename = fileService.uploadFile(itemImgLocation, originalFilename, itemImgFile.getBytes());
//                itemDto.setItemImage("/images/item/" + savedFilename);
//            }
//
//            itemService.insertItem(itemDto);
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("errorMessage", "상품 등록 중 오류가 발생했습니다.");
//            model.addAttribute("product", itemForm);
//            return "addProductAdd";
//        }
//
//        return "redirect:/";
//    }

    @GetMapping("/item/{itemNum}")
    public String itemDetail(Model model, @PathVariable("itemNum") Long itemNum, Principal principal){

        if (principal == null) {
            model.addAttribute("loginErrorMsg","로그인 후 사용가능합니다.");
            return "login";
        }

        ItemDto itemDto = itemService.selectItem(itemNum);
        model.addAttribute("item", itemDto);

        //로그인한 cust_id 확인
        System.out.println(principal.getName());

        return "item";
    }

    //검색어
    @PostMapping("/search")
    public String searchWord(@RequestParam("word") String word, Model model){
        System.out.println(word);
        System.out.println("상품 검색 컨트롤러 실행");

        List<ItemDto> list = itemService.searchWord(word);

        model.addAttribute("searchItems", list);
        model.addAttribute("word", word);

        return "/search.html";
    }

}
