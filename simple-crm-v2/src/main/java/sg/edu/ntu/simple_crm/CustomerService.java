package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

// An interface is like contract, anything that implements this has to have the below methods
// it also acts like a middle-man device that when you switch to an improved CustomerServiceImpl code like switching App version, you can continue to refer to CustomerService in the Controller.java, and Spring boot will detect and find the correct version (but must set that improved CustomerServiceImpl.java as a bean and also @Primary if there are multiple versions implementing same interface, for Springboot to find the correct version to inject)
public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer getCustomer(Long id);
    ArrayList<Customer> getAllCustomers();

    Customer updateCustomer (Long id, Customer customer);

    void deleteCustomer(Long id);

    Interaction addInteractionToCustomer(Long id, Interaction interaction);
}
