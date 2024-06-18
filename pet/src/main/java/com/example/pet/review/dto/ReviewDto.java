package com.example.pet.review.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {

    private int reviewNum;
    private String reviewTitle;
    private String custName;
    private String itemName;
    private Long custNum;
    private int itemNum;
    private LocalDateTime reviewDate;
    private String reviewContent;
    private String reviewImg;
}
