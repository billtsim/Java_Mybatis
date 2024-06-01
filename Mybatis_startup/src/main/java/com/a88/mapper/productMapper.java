package com.a88.mapper;

import com.a88.Pojo.products;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface productMapper {

    List<products> allProducts(String name, String category, String tags, Double minPrice, Double maxPrice);

    void update(products pro);

    void delete(ArrayList<Integer> ids);
}
