package com.a88.controller;

import com.a88.Pojo.products;
import com.a88.Pojo.result;
import com.a88.service.inter.productService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/product")
@RestController
@Slf4j
public class productController {

    @Autowired
    private productService PS;

    @GetMapping
    public result allProducts(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String category,
                              @RequestParam(required = false) String tags,
                              @RequestParam(required = false) Double minPrice,
                              @RequestParam(required = false) Double maxPrice) {
        log.info("get all products info from database");
        List<products> products = PS.allProducts(name, category, tags, minPrice, maxPrice);

        return result.success(products);
    }

    @PutMapping
    public result update(@RequestParam("id") Long id,
                         @RequestParam("name") String name,
                         @RequestParam("description") String description,
                         @RequestParam("originalPrice") Double originalPrice,
                         @RequestParam("category") String category,
                         @RequestParam("tags") String tags,
                         @RequestParam("discount") Double discount,
                         @RequestParam(value = "image", required = false) MultipartFile image,
                         @RequestParam(value = "oldImageUrl", required = false) String oldImageUrl) throws IOException {
        log.info("update game: {}, {}, {}, {}, {}, {}, {}, {}, {}", id, name, description, originalPrice, category, tags, discount, image, oldImageUrl);

        PS.update(id,
                name,
                description,
                originalPrice,
                category,
                tags,
                discount,
                image,
                oldImageUrl);
        return result.success();
    }

    @DeleteMapping("/{ids}")
    public result delete(@PathVariable ArrayList<Integer> ids) {
        log.info("delete operation, ids:{}", ids);
        PS.delete(ids);
        return result.success();
    }

}
