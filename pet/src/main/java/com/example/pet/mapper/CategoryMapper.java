package com.example.pet.mapper;

import com.example.pet.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    int insertCate(CategoryDto categoryDto);
}
