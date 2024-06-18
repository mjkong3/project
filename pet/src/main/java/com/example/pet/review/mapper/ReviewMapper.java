package com.example.pet.review.mapper;

import com.example.pet.review.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    // 리뷰 추가
    int insertRV (ReviewDto reviewDto);

    // 리뷰 목록
    List<ReviewDto> listRVAll();

    // 리뷰 검색
    ReviewDto selectRVInfo(int reviewNum);

    // 리뷰 삭제
    int deleteRV(int reviewNum);

    // 리뷰 수정
    int updateRV(Map map);

    // 조회 수
    @Update("UPDATE review SET hit = hit + 1 WHERE review_num = #{reviewNum}")
    void increaseRVHit(@Param("reviewNum") int reviewNum);

}
