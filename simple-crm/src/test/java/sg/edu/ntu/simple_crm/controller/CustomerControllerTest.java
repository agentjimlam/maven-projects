package sg.edu.ntu.simple_crm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.edu.ntu.simple_crm.entity.Customer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testGetCustomerById() throws Exception {
    // Step 1: Build a GET request to /customers/1
    RequestBuilder request = MockMvcRequestBuilders.get("/customers/1");

    // Step 2: Perform the request, get response and assert
    mockMvc.perform(request)
        // Assert that the status code is 200 OK
        .andExpect(status().isOk())
        // Assert that the content type is JSON
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // Assert that the id returned is 1
        .andExpect(jsonPath("$.id").value(1));
  }

  @Test
  public void testGetAllCustomers() throws Exception {
    // Step 1: Build request
    RequestBuilder request = MockMvcRequestBuilders.get("/customers");

    // Step 2: Perform the request and assert
    mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.size()").value(5));

  }

  @Test
  public void testValidCustomerCreation() throws Exception {
    // Step 1: Create customer
    Customer newCustomer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
        .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

    // Step 2: Convert Java to JSON
    String newCustomerAsJson = objectMapper.writeValueAsString(newCustomer);

    // Step 3: Build the request
    RequestBuilder request = MockMvcRequestBuilders.post("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newCustomerAsJson);

    // Step 4: Perform the request, get response and assert
    mockMvc.perform(request)
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(5))
        .andExpect(jsonPath("$.firstName").value("Clint"))
        .andExpect(jsonPath("$.lastName").value("Barton"));

  }

  @Test
  public void testInvalidCustomerCreation() throws Exception {
    // Create customer with invalid fields
    Customer invalidCustomer = new Customer(3L, "", "Banner", "bruce@a.com", "12345678", "Manager", 1990, null);

    // Convert object to JSON
    String invalidCustomerAsJson = objectMapper.writeValueAsString(invalidCustomer);

    // Build the request
    RequestBuilder request = MockMvcRequestBuilders.post("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(invalidCustomerAsJson);

    // Perform request
    mockMvc.perform(request)
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

  }

}

// RequestBuilder is a class provided by Spring's MockMvc framework to help construct HTTP requests in a test environment
