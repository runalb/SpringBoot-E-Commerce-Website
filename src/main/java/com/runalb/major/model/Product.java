package com.runalb.major.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    private double price;

    private String description;

    private String imageName;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Order> orders;

}
