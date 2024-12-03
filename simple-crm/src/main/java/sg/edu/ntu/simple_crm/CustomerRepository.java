package sg.edu.ntu.simple_crm;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Structure: [Operation]By[FieldName][Condition]
    // find By FirstName

    // Custom query to find all customers with a specific first name
    // List<Customer> findByFirstName(String firstName);
    List<Customer> findByFirstNameIgnoreCase(String firstName);

    // Find all customers with no interactions
    List<Customer> findByInteractionsIsEmpty();
}

// JPA handles all of this