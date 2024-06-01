package com.a88.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class products {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double originalPrice;
    private Double discount; // 折扣百分比
    private String category;
    private String imageUrl;
    private String tags;
    private LocalDateTime createTime; // 新添加的字段
    private LocalDateTime updateTime;
}
