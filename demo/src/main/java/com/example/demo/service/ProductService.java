package com.example.demo.service;

import com.example.demo.model.Product;
//import  com.example.demo.specification.ProductSpecifications;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.SaveProductRequest;
import com.example.demo.request.SearchProductRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import  java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // method implementation for creating a project
    public Product createProduct(SaveProductRequest request){

        if(Objects.nonNull(request)){

            Product product = new Product();
            product.setName(request.getName());;
            product.setPrice(request.getPrice());
            product.setQuantity(request.getQuantity());

            return productRepository.save(product);
        }
        // handle exception here

      return  null;


    }

    // method implementation for product creation ends here

    public Product getProductById(Long id){
        return  productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts(){

        return  productRepository.findAll();
    }



    public Product updateProduct( Long id, SaveProductRequest request){


        Product existingProduct = productRepository.findById(id).orElse(null);
//        if(existingProduct != null )
        if(Objects.nonNull(existingProduct) )   {   // good practice objects.nonnull
             existingProduct.setName(request.getName());
             existingProduct.setPrice(request.getPrice());
             existingProduct.setQuantity(request.getQuantity());
             return  productRepository.save(existingProduct);
        }

        return  null;

    }

    public  void  deleteProduct(Long id){

        try{
            productRepository.deleteById(id);
        }catch (Error e){
            throw new IllegalArgumentException("invalid id ");
        }

    }



    // for searching of all products with all parameters

    public  List<Product> searchProducts(SearchProductRequest request){


//        Specification<Product> spec = ProductSpecifications.neutral();
//
//            if (id != null) {
//                spec = spec.and(ProductSpecifications.withId(id));
//            }
//            if (name != null) {
//                spec = spec.and(ProductSpecifications.withName(name));
//            }
//            if (price != null) {
//                spec = spec.and(ProductSpecifications.withPrice(price));
//            }
//            if (quantity != null) {
//                spec = spec.and(ProductSpecifications.withQuantity(quantity));
//            }
//
//            return productRepository.findAll(spec);
        // Optional: Add validation/business logic here
        if (Objects.nonNull(request.getPrice()) && request.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        Long id = request.getId();
        String name = request.getName();
        Double price = request.getPrice();
        Integer quantity = request.getQuantity();

        return productRepository.searchProducts(id, name, price, quantity);
        }

        // for searching product method ends here

    // for searching of all api



    // update service starts here



    @Transactional   // used for update all the events in a single go .
    public List<Product> updateProducts(
            SearchProductRequest parmdata,
            SaveProductRequest updatedata
    ) {
        List<Product> products = productRepository.findProductsToUpdate(
               parmdata.getId(), parmdata.getName(), parmdata.getPrice(), parmdata.getQuantity()
        );
        if (products.isEmpty()) {
            throw new RuntimeException("No matching products found");
        }

        products.forEach(product -> {
            // Only update non-null fields from request
            if (Objects.nonNull(updatedata.getName()) ) {
                product.setName(updatedata.getName());
            }
            if (Objects.nonNull(updatedata.getPrice()) ) {
                product.setPrice(updatedata.getPrice());
            }
            // set new quantity
            if (Objects.nonNull(updatedata.getQuantity()) ) {
                product.setQuantity(updatedata.getQuantity());
            }
        });

        return productRepository.saveAll(products);
    }
}

    // update service ends here





