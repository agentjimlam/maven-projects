package sg.edu.ntu.simple_crm;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

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
        customerRepository.save(new Customer("Tony", "Stark"));
        customerRepository.save(new Customer("Bruce", "Banner"));
        customerRepository.save(new Customer("Peter", "Parker"));
        customerRepository.save(new Customer("Stephen", "Strange"));
    }

}
