package sg.edu.ntu.simple_crm.config;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

// Repository allows me to write data to database
@Component
public class DataLoader {
    private CustomerRepository customerRepository;

    // Constructor injection
    // @Autowired
    public DataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void loadData() {
        customerRepository.deleteAll();
        // Load data
        // customerRepository.save(new Customer("Tony", "Stark"));
        // customerRepository.save(new Customer("Bruce", "Banner"));
        // customerRepository.save(new Customer("Peter", "Parker"));
        // customerRepository.save(new Customer("Stephen", "Strange"));

        // bc we set validation on yearofbirth and contactNo, above will give error.
        // One way to resolve is simply make Customer objects via All Arguments
        // constructor
        // or use @Builder method

        // Customer c1 =
        // Customer.builder().firstName("Tony").lastName("Stark").email("tony@a.com").contactNo("12345678").yearOfBirth(1975).build();
        customerRepository.save(Customer.builder().firstName("Tony").lastName("Stark").email("tony@gamil.com").contactNo("12345678").yearOfBirth(1975).build());
        customerRepository.save(Customer.builder().firstName("Bruce").lastName("Wayne").email("weeeee@abba.com").contactNo("12345678").yearOfBirth(1985).build());
        customerRepository.save(Customer.builder().firstName("Peter").lastName("Parker").email("parker@a.com").contactNo("12345678").yearOfBirth(1995).build());
        customerRepository.save(Customer.builder().firstName("Stephen").lastName("Strange").email("stephen@a.com").contactNo("12345678").yearOfBirth(1980).build());
    }

}
