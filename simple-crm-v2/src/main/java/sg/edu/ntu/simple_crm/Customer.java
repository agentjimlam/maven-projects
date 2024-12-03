package sg.edu.ntu.simple_crm;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;

// import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // tells JPA this class has to be mapped to a database
@Table(name = "customer")
// @Component
public class Customer {
    // private final String id = UUID.randomUUID().toString();
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    // ignores customer inside the interactions list object
    @JsonIgnoreProperties("customer")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) // this allows us to delete interaction that is tightly coupled with customer object
    private List<Interaction> interactions;

    // Constructors
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

// This simple-crm is a monolithic architecture, everything is inside one project. Outside is moving towards microservice architecture, multiple small projects connected to database.
// RestTemplate

// spring IOC / application context, same thing