package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;


// all the CRUD (Read, write, delete) code is moved here from CustomerController.java
@Repository
public class CustomerRepository {
    private ArrayList<Customer> customers = new ArrayList<>();

    // Preload data
    public CustomerRepository() {
        customers.add(new Customer("Bruce", "Banner"));
        customers.add(new Customer("Peter", "Parker"));
        customers.add(new Customer("Stephen", "Strange"));
        customers.add(new Customer("Steve", "Rogers"));
    }

    // Create
    public Customer createCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    // Read - Get one
    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    // Read - Get all
    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    // Update
    public Customer updateCustomer(int index, Customer customer) {
        Customer customerToUpdate = customers.get(index);
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setContactNo(customer.getContactNo());
        customerToUpdate.setJobTitle(customer.getJobTitle());
        customerToUpdate.setYearOfBirth(customer.getYearOfBirth());
        return customerToUpdate;
    }

    // Delete
    public void deleteCustomer(int index) {
        customers.remove(index);
    }
}
