package com.runalb.major.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private String address;

    private String postcode;

    private String city;

    private String phone;

    @Column(nullable = false)
    @NotEmpty
    @Email(message = "{errors.invaild_email}")
    private String email;

    private double totalAmount;



    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_details",
            joinColumns = {@JoinColumn(name = "Order_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "Product_ID", referencedColumnName = "ID")}
    )
    @Column(nullable = false)
    @NotEmpty
    private List<Product> products;

}
