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
import java.util.List;

@Service
public class productsServiceImp implements productService {

    @Autowired
    private productMapper PM;

    @Autowired
    private uploadFileUtil ULF;
    @Override
    public List<products> allProducts(String name, String category, String tags, Double minPrice, Double maxPrice) {
        return PM.allProducts(name, category, tags, minPrice, maxPrice);
    }

    @Override
    public void update(Long id, String name, String description, Double originalPrice, String categories, String tags, Double discount, MultipartFile image, String oldImageUrl) throws IOException {
        products pro = new products();
        pro.setId(id);
        pro.setName(name);
        pro.setDescription(description);
        pro.setOriginalPrice(originalPrice);
        pro.setCategories(categories);
        pro.setTags(tags);
        pro.setDiscount(discount);
        pro.setUpdateTime(LocalDateTime.now());
        if (image != null) {
            ULF.deleteFile(oldImageUrl);
            pro.setImageUrl(ULF.uploadFile(image));
        }
        PM.update(pro);
    }

    @Override
    public void delete(ArrayList<Integer> ids, String imageFileName) {
        ULF.deleteFile(imageFileName);
        PM.delete(ids);
    }

    @Override
    public void add(String name, String description, Double originalPrice, String categories, String tags, Double discount, MultipartFile image) throws IOException {
        products pro = new products();
        pro.setName(name);
        pro.setDescription(description);
        pro.setOriginalPrice(originalPrice);
        pro.setCategories(categories);
        pro.setTags(tags);
        pro.setDiscount(discount);
        pro.setUpdateTime(LocalDateTime.now());
        pro.setCreateTime(LocalDateTime.now());
        pro.setImageUrl(ULF.uploadFile(image));
        PM.add(pro);
    }
}
