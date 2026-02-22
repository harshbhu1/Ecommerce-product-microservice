package com.example.demo.response;
import  com.example.demo.model.Product;

import  java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SearchAllResponse {

    private  boolean status;
    private  String message;
    private  List<Product>data;



}
