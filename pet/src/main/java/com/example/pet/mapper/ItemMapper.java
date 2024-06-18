package com.example.pet.mapper;

import com.example.pet.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    int insertItem(ItemDto itemDto);

    List<ItemDto> itemAll();

    ItemDto selectItem(Long num);

    int updateCnt(ItemDto itemDto);

    List<ItemDto> selectCate(String categoryName);
    //검색어
    List<ItemDto> searchItem(String searchWord);
}
