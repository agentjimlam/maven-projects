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

/*
 * Controller (Req/Res) <-> Service (Business Logic) <-> Repository (CRUD datastore)
 */

// Controller Layer: Handles HTTP requests and responses, acting as the entry point for the application.
// 

// Controller handles request from service layer, talks to Service to do things. Service tells Repository layer to do things.
@RestController // Combines @Controller + @ResponseBody
// @RequestMapping("/v1/customers")
@RequestMapping("/customers")
public class CustomerController {

  // private CustomerService customerService = new CustomerService;
  private CustomerService customerService;

  // Constructor Injection
  // @Autowired
  // @Qualifier lets you specify the bean name for the injection
  // public CustomerController(@Qualifier("customerServiceImpl") CustomerService
  // customerService) {
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService; // When Spring Boot see the customer service type, Spring will "oh you want
                                            // a CustomerService type of bean", Spring will look for which has
                                            // implements CustomerService
  }

  // private ArrayList<Customer> customers = new ArrayList<>();

  // public CustomerController(){
  // customers.add(new Customer("Bruce", "Banner"));
  // customers.add(new Customer("Peter", "Parker"));
  // customers.add(new Customer("Stephen", "Strange"));
  // customers.add(new Customer("Steve", "Rogers"));
  // customers.add(new Customer("Diana", "Price"));
  // }

  /// Create a customer
  @PostMapping("")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer newCustomer = customerService.createCustomer(customer);
    // return customer;
    return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
  }

  // Read - get all customers
  @GetMapping("")
  public ResponseEntity<ArrayList<Customer>> getAllCustomers() {
    ArrayList<Customer> allCustomers = customerService.getAllCustomers();
    return new ResponseEntity<>(allCustomers, HttpStatus.OK);
  }

  // Read - get one customer
  // localhost:8080/customers/35623130-bade-43d6-9bf4-6ea7189301fb
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    try {
      Customer foundCustomer = customerService.getCustomer(id);
      return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }

  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
    try {
      Customer updatedCustomer = customerService.updateCustomer(id, customer);
      return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
    try {
      customerService.deleteCustomer(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Nested route - add interaction to customer
  @PostMapping("/{id}/interactions")
  public ResponseEntity<Interaction> addInteractionToCustomer(@PathVariable Long id,
      @RequestBody Interaction interaction) {
    Interaction newInteraction = customerService.addInteractionToCustomer(id, interaction);
    return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
  }


}

// @Controller -> Returns views (HTML, Thymeleaf templates)
// @RestController -> Returns data (like JSON) for REST APIs
// @RequestBody converts the received JSON to object during GET requests