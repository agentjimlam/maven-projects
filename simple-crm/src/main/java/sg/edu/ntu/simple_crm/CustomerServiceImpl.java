package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

// business logic is all put here. just to access functions. Things like getters and setters, finding ArrayList index

// why we need interface, eg. we want make CustomerServiceImprovedVersion2.java
// @Primary
@Service // this makes it a bean alr
public class CustomerServiceImpl implements CustomerService {

    // private CustomerRepository customerRepository = new CustomerRepository();
    private CustomerRepository customerRepository;

    // Constructor injection
    public CustomerServiceImpl (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    
    public Customer createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }

    public Customer getCustomer(String id) {
        return customerRepository.getCustomer(getCustomerIndex(id));
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Customer updateCustomer(String id, Customer customer) {
        return customerRepository.updateCustomer(getCustomerIndex(id), customer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteCustomer(getCustomerIndex(id));
    }

    // Helper FUNCTION
    private int getCustomerIndex(String id) {
        for (Customer customer : customerRepository.getAllCustomers()) {
            if (customer.getId().equals(id)) {
                return customerRepository.getAllCustomers().indexOf(customer);
            }
        }
        // return -1;
        throw new CustomerNotFoundException(id);
    }
}

// Controller handles request from web app or API client or like Postman
// Service handles business logic, talks between controller and repository layers
// Repo layer: manages data access and communicates with database

// Controller, Service, Repo layers are all components, Springboot detects them as beans during component scanning