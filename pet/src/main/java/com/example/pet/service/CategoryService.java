package com.example.pet.service;

import com.example.pet.dto.CategoryDto;
import com.example.pet.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public int insertCate(CategoryDto categoryDto){

        int result = categoryMapper.insertCate(categoryDto);
        return result;
    }
}
