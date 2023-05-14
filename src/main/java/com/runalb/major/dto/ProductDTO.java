package com.runalb.major.dto;

import lombok.Data;


@Data
public class ProductDTO {
    private long id;
    private String name;
    private int categoryId;
    private double price;
    private String description;
    private String imageName;
}
