package sg.edu.ntu.simple_crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.exception.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    public void testCreateCustomer() {
        // 1. SETUP
        // Create a customer
        Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("gogo@a.com")
                .contactNo("12345678").jobTitle("archer").yearOfBirth(1995).build();

        // Mock the save method the customerRepository
        when(customerRepository.save(customer)).thenReturn(customer);

        // 2. EXECUTE
        // Call the method that we want to test
        Customer savedCustomer = customerService.createCustomer(customer);

        // 3. ASSERT
        assertEquals(customer, savedCustomer, "The saved customer should be the same as the new customer");

        // verify that the save method was called exactly one time
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testGetCustomer() {
        // 1. SETUP
        Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@a.com")
                .contactNo("12345678").jobTitle("Agent").yearOfBirth(1975).build();
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // 2. EXECUTE
        Customer retrievedCustomer = customerService.getCustomer(customerId);

        // 3. ASSERT
        assertEquals(customer, retrievedCustomer);

    }

    @Test
    public void testGetCustomerNotFound(){
        
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));
    }
}

// mockito makes the mock data for testing. JUNIT does the error catching in tests.

// to make sure when comparing objects, we are comparing the contents and not
// the reference point of the object,
// we use @EqualsAndHashCode in the Customer.java
