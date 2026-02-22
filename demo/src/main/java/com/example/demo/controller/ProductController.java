package com.example.demo.controller;
import  com.example.demo.model.Product;


//IllegalArgumentException (Invalid input)
//IllegalStateException (Invalid object state)
//UnsupportedOperationException (Unsupported action)


import com.example.demo.request.SaveProductRequest;
import com.example.demo.request.SearchProductRequest;
import com.example.demo.request.UpdateProductRequest;
import com.example.demo.response.ErrorResponse;
import com.example.demo.response.SearchAllResponse;
import  com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import  java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {



    @Autowired // used as constructor it initializes the value when the application starts
    private ProductService productService;

    // post api for creating the product
    @PostMapping("/save") // shows the route for the post mapping
    public ResponseEntity<?>createProduct(@RequestBody SaveProductRequest product){ // @requestBody shows what the data getting from the request

        // calling the product service's createProduct method
        if(Objects.isNull(product.getName()) ||  Objects.isNull(product.getPrice()) || Objects.isNull(product.getQuantity())){
            throw new IllegalArgumentException("all arguments are required!");
        }

        Product savedProduct = productService.createProduct(product);
        // in case of product is not found
        if(Objects.nonNull(savedProduct)){

            return ResponseEntity.ok(savedProduct);
          }

        // handle the exception here in case of product is null throw an error
        throw new RuntimeException("product is null");

    }
    // get api for fetching single product

    @GetMapping("/{id}")   // @GetMapping for the mapping of the get request
    public ResponseEntity<?> getProduct(@PathVariable Long id){ // path variable for getting the id of the product through the dynamic routes

     try{

         Product product = productService.getProductById(id);
         if(Objects.isNull(product)){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "product not found!"));
         }

         SearchAllResponse res = SearchAllResponse.builder().status(true).message(" single product successfully searched!").data(Collections.singletonList(product)).build();

         return ResponseEntity.ok(res);

     } catch (Exception e){

         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error processing request:" + e.getMessage()));

     }


    }



    // get api for fetching all product

    @GetMapping("/getAllProducts") // Get mapping for getting all the products
    public ResponseEntity<?> getAllProducts(){

        try {
            List<Product> products = productService.getAllProducts();

            if(Objects.isNull(products)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "product not found!"));
            }
            SearchAllResponse res = SearchAllResponse.builder().status(true).
                                    message("All products successfully fetched!").
                                    data(products).build();

              return ResponseEntity.ok(res);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }


    }



    // put api for updating the product
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody SaveProductRequest request){

        try{
            if(Objects.isNull(id)){
                throw  new IllegalArgumentException("please send all valid arguments!");
            }
            // check that all the fields of the product exists

            if(Objects.isNull(request.getName()) || Objects.isNull(request.getPrice()) || Objects.isNull(request.getQuantity())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.NO_CONTENT.value(), "please provide all the required detail of the product"));
            }
            // sets the id of the product because in request body id doesn't exist.

            Product updatedProduct   = productService.updateProduct(id,request);
            if(Objects.isNull(updatedProduct)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.NO_CONTENT.value(), "product not found to update"));
            }

            return ResponseEntity.status(200).body(updatedProduct);
        }
        catch (Exception e)
        {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Getting Internal Server Error"));
        }

    }


    // delete api for deleting the product
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){

        try{
            // if id null the handle the exception
            if(Objects.isNull(id)){
                throw new IllegalArgumentException("id could not be Empty");
            }
            Product product = productService.getProductById(id);
            if(Objects.isNull(product)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "product not found!"));
            }
            // delete service method called
            productService.deleteProduct(id);

            return ResponseEntity.ok("product successfully deleted");
        }catch (Error e){

           throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "an unexpected error occurred",e);

        }


    }



    // api end points for the search with all parameters

    @PostMapping("/search")
    public ResponseEntity<?> searchProducts(
            @RequestBody SearchProductRequest request
            ) {
        log.info(request.getName());

        try{
            List<Product> products = productService.searchProducts(request);

            if (products.isEmpty()) {
                return ResponseEntity.ok("there is no product in cart");
            }

            SearchAllResponse res = SearchAllResponse.builder().status(true).message("successfully fetched all matched products").data(products).build();
            return ResponseEntity.status(HttpStatus.OK).body(res);



        }catch (Exception e){

            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "an unexpected error occurred",e);
        }

    }


    // end of the search api

    // update api implementation starts here

    @PostMapping("/newupdate")
    public ResponseEntity<?> updateProducts(

            @RequestBody UpdateProductRequest updateRequest) {

           SearchProductRequest parmdata = new SearchProductRequest();
           parmdata.setId(updateRequest.getId());
           parmdata.setName(updateRequest.getName());
           parmdata.setPrice(updateRequest.getPrice());
           parmdata.setQuantity(updateRequest.getQuantity());
// for the get data
           SaveProductRequest updatedata = new SaveProductRequest();
           updatedata.setName(updateRequest.getNewname());
           updatedata.setPrice(updateRequest.getNewprice());
           updatedata.setQuantity(updateRequest.getNewquantity());

        // Validate at least one search parameter is provided
        if ( Objects.isNull(parmdata.getId()) && Objects.isNull(parmdata.getName()) && Objects.isNull(parmdata.getPrice()) && Objects.isNull(parmdata.getQuantity())) {
            return ResponseEntity.badRequest()
                    .body("At least one search parameter (name, price, or quantity) must be provided");
        }

        // Validate at least one update field is provided
        if ( Objects.isNull(updatedata.getName() )
                && Objects.isNull(updatedata.getPrice() )
                &&  Objects.isNull(updatedata.getQuantity())) {
            return ResponseEntity.badRequest()
                    .body("At least one update field (newName, newPrice, or newQuantity) must be provided");
        }

        try {
            List<Product> updatedProducts = productService.updateProducts(
                    parmdata,updatedata);

            return ResponseEntity.ok(updatedProducts);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    // update api implementation ends here

}



