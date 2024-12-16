package sg.edu.ntu.simple_crm.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.simple_crm.entity.Product;
import sg.edu.ntu.simple_crm.exception.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.exception.ProductNotFoundException;


@RestController
public class ProductController {
    private ArrayList<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product("Gameboy", "From Nintendp", 50));
        products.add(new Product("XBOX Series X", "From USA", 799));
        products.add(new Product("PS4", "From Sony", 41));
        products.add(new Product("PC", "From Singapore", 2000.40));
        products.add(new Product("PS5", "From Singapore", 1069.00));
    }

    // Create a product
    @PostMapping("/products")
    public ResponseEntity<Product> createCustomer(@RequestBody Product product) {
        products.add(product);
        // return customer;
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // Read - get all products
    @GetMapping("/products")
    public ResponseEntity<ArrayList<Product>> getAllCustomers() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Read - get one product
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        try {
            int index = getProductIndex(id);
            return new ResponseEntity<>(products.get(index), HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }

    }

    // Update
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        try {
            int index = getProductIndex(id);
            products.set(index, product);
            Product updatedProduct = products.get(index);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String id) {
        try {
            int index = getProductIndex(id);
            products.remove(index);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Helper FUNCTION
    private int getProductIndex(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return products.indexOf(product);
            }
        }
        // return -1;
        throw new ProductNotFoundException(id);
    }
}
