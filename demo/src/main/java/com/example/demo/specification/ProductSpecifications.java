package com.example.demo.specification;
import  com.example.demo.model.Product;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecifications {

    // Neutral spec (replacement for where(null))
    // this method executes when all the argument is null
    // similar sql query(Select * from products ;)
    public static Specification<Product> neutral() {
        return (root, query, cb) -> cb.conjunction();
    }

    // for fetching the product with given id
    // similar sql query ( select * from product p where p.id = id;)
    public static Specification<Product> withId(Long id) {
        return (root, query, cb) ->
                id == null ? null : cb.equal(root.get("id"), id);
    }
    // similar to sql query (select * from products p where p.name = name)
    public static Specification<Product> withName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.equal(root.get("name"), name);
    }

    // similar to sql query (select * from products p where p.price = price)
    public static Specification<Product> withPrice(Double price) {
        return (root, query, cb) ->
                price == null ? null : cb.equal(root.get("price"), price);
    }

    // for fetching the product with given id
    // similar sql query ( select * from product p where p.quantity = quantity;)
    public static Specification<Product> withQuantity(Integer quantity) {
        return (root, query, cb) ->
                quantity == null ? null : cb.equal(root.get("quantity"), quantity);
    }
}
