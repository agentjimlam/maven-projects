package sg.edu.ntu.simple_crm.entity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private double price;
    
    // Constructors
    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
}
