package com.a88.service.impl;

import com.a88.Pojo.products;
import com.a88.utils.uploadFileUtil;
import com.a88.mapper.productMapper;
import com.a88.service.inter.productService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class productsServiceImp implements productService {

    @Autowired
    private productMapper PM;

    @Autowired
    private uploadFileUtil ULF;
    @Override
    public List<products> allProducts(String name, String categories, String tags, Double minPrice, Double maxPrice) {
        return PM.allProducts(name, categories, tags, minPrice, maxPrice);
    }

    @Override
    public void update(Long id, String name, String description, Double originalPrice, String categories, String tags, Double discount, MultipartFile[] images, String[] oldImageUrl) throws IOException {
        // 处理 tags，去除多余空格

        String[] tagsArray = tags.split(",");
        String trimmedTags = Arrays.stream(tagsArray)
                .map(String::trim)
                .collect(Collectors.joining(","));

        // 处理 categories，去除多余空格
        String[] cateArray = categories.split(",");
        String trimmedCate = Arrays.stream(cateArray)
                .map(String::trim)
                .collect(Collectors.joining(","));

        products pro = new products();
        pro.setId(id);
        pro.setName(name);
        pro.setDescription(description);
        pro.setOriginalPrice(originalPrice);
        pro.setCategories(trimmedCate);
        pro.setTags(trimmedTags);
        pro.setDiscount(discount);
        pro.setUpdateTime(LocalDateTime.now());
        ULF.deleteFiles(oldImageUrl);
        StringBuilder imageUrl2 = new StringBuilder();
        for (MultipartFile multipartFile : images) {
            imageUrl2.append(ULF.uploadFile(multipartFile));
            imageUrl2.append(",");
        }
        pro.setImageUrl(imageUrl2.toString());
        PM.update(pro);
    }

    @Override
    public void delete(ArrayList<Integer> ids, String imageFileName) {
        ULF.deleteFile(imageFileName);
        PM.delete(ids);
    }

    @Override
    public void add(String name, String description, Double originalPrice, String categories, String tags, Double discount, MultipartFile[] image) throws IOException {
        products pro = new products();
        pro.setName(name);
        pro.setDescription(description);
        pro.setOriginalPrice(originalPrice);
        pro.setCategories(categories);
        pro.setTags(tags);
        pro.setDiscount(discount);
        pro.setUpdateTime(LocalDateTime.now());
        pro.setCreateTime(LocalDateTime.now());
        System.out.println(image);
        System.out.println(image.toString());
        StringBuilder imageUrl = new StringBuilder();
        for (MultipartFile multipartFile : image) {
            imageUrl.append(ULF.uploadFile(multipartFile));
            imageUrl.append(",");
        }
        pro.setImageUrl(imageUrl.toString());
        PM.add(pro);
    }
}
