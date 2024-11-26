package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

// Activity
// 1. Create Product POJO
// 2. Create ProductController
// 3. Add in CRUD routes
// 4. Handle exceptions

@RestController // Combines @Controller + @ResponseBody
// @RequestMapping("/v1/customers")
@RequestMapping("/customers")
public class CustomerController {

  private ArrayList<Customer> customers = new ArrayList<>();

  public CustomerController(){
    customers.add(new Customer("Bruce", "Banner"));
    customers.add(new Customer("Peter", "Parker"));
    customers.add(new Customer("Stephen", "Strange"));
    customers.add(new Customer("Steve", "Rogers"));
    customers.add(new Customer("Diana", "Price"));
  }

  /// Create a customer
  @PostMapping("")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    customers.add(customer);
    // return customer;
    return new ResponseEntity<>(customer, HttpStatus.CREATED);
  }

  // Read - get all customers
  @GetMapping("")
  public ResponseEntity<ArrayList<Customer>> getAllCustomers() {
      return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  // Read - get one customer
  // localhost:8080/customers/35623130-bade-43d6-9bf4-6ea7189301fb
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
    try {
      int index = getCustomerIndex(id);
      return new ResponseEntity<>(customers.get(index), HttpStatus.OK);
    } catch (CustomerNotFoundException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }
    
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
    try {
      int index = getCustomerIndex(id);
      customers.set(index, customer);
      Customer updatedCustomer = customers.get(index);
      return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
  }

  // Delete
  // @DeleteMapping("/customers/{id}")
  // public Customer deleteCustomer(@PathVariable String id) {
  //   int index = getCustomerIndex(id);
  //   return customers.remove(index);
  // }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String id) {
    try {
      int index = getCustomerIndex(id);
      customers.remove(index);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Helper FUNCTION
  private int getCustomerIndex(String id) {
    for (Customer customer : customers) {
      if (customer.getId().equals(id)) {
        return customers.indexOf(customer);
      }
    }
    // return -1;
    throw new CustomerNotFoundException(id);
  }
  
  
}

// @Controller -> Returns views (HTML, Thymeleaf templates)
// @RestController -> Returns data (like JSON) for REST APIs
// @RequestBody converts the received JSON to object during GET requests