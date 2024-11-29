package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Okay so to demo why we need interface, eg. we want to improve out CustomerServiceImpl, make one that has logging function too

@Primary
@Service
public class CustomerServiceWithLoggingImpl implements CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceWithLoggingImpl.class);
    private CustomerRepository customerRepository;

    // Constructor injection
    public CustomerServiceWithLoggingImpl (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    
    public Customer createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }

    public Customer getCustomer(String id) {
        return customerRepository.getCustomer(getCustomerIndex(id));
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        logger.info("✅ CustomerServiceWithLoggingImpl.getAllCustomers called");
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
