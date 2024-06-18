package com.example.pet.review.controller;

import com.example.pet.review.dto.ReviewDto;
import com.example.pet.review.form.ReviewForm;
import com.example.pet.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping("/review/new")
    public String reviewForm(Model model, HttpServletRequest request){
        model.addAttribute("reviewForm", new ReviewForm());
        return "/review/reviewForm.html";
    }

    // 리뷰 등록
    @PostMapping("/review/new")
    public String reviewNew(@Valid ReviewForm reviewForm,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        System.out.println("reviewController (reviewNew 실행)");

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "공지사항 등록중 오류발생");
            System.out.println("에러발생");
            return "review/reviewForm.html";
        }

        try {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setReviewTitle(reviewForm.getTitle());
            reviewDto.setReviewContent(reviewForm.getContent());
            reviewService.addRV(reviewForm);
            System.out.println(reviewForm.toString());
            redirectAttributes.addFlashAttribute("newReview", "공지사항 등록 완료");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "공지사항 등록중 오류발생");
        }

        return "redirect:/review";
    }

    // 리뷰 상세보기
    @GetMapping("/review/{reviewNum}")
    public String reviewDtl(@PathVariable int reviewNum, Model model) {
        try {
            // 클릭된 공지의 번호를 통해 조회수 증가
            //reviewService.increaseRVHit(reviewNum);
            ReviewDto reviewDto = reviewService.selectRV(reviewNum);
            model.addAttribute("review", reviewDto);
        } catch (NullPointerException e) {
            model.addAttribute("errorMessage", "존재하지 않는 공지입니다");
            return "redirect:/review";
        }
        return "review/reviewDtl.html";
    }

    // 리뷰 수정
    @PostMapping("/review/{reviewNum}")
    public String reviewUpdate(@PathVariable int reviewNum,
                               ReviewForm reviewForm,
                               Model model){

        Map<String, Object> map = new HashMap<>();
        map.put("reviewTitle", reviewForm.getTitle());
        map.put("reviewContent", reviewForm.getContent());
        map.put("reviewNum", reviewNum);

        try{
            reviewService.updateRV(map);
        }catch (NullPointerException e){
            model.addAttribute("errorMessage", "공지 수정중 오류발생");
            return "redirect:/review";
        }

        return "redirect:/review";
    }

    // 리뷰 삭제
    @GetMapping("/review/delete/{reviewNum}")
    public String reviewDelete(@PathVariable int reviewNum,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        try {
            reviewService.deleteRV(reviewNum);
            redirectAttributes.addFlashAttribute("successMessage", "공지가 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "공지 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/review";
    }

    // 리뷰 목록
    @GetMapping("/review")
    public String reviewList(Model model){
        List<ReviewDto> reviewDtoList = reviewService.RVList();
        model.addAttribute("reviews", reviewDtoList);
        return "/review/review.html";
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

/*    // 리뷰 조회수 증가
    @PostMapping("/review/increaseRVHit")
    @ResponseBody
    public String increaseRVHit(@RequestParam("reviewNum") int reviewNum) {
        reviewService.increaseRVHit(reviewNum);
        return "성공";
    }*/
}
