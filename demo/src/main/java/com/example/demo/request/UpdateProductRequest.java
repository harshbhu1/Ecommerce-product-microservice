package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProductRequest {

    private Long id;
    private String name;
    private  Double price;
    private  Integer quantity;

    private  String newname;
    private  Double newprice;
    private  Integer newquantity;
}
