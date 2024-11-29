package sg.edu.ntu.simple_crm;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private final String id = UUID.randomUUID().toString();
    private String description;
    private Double amount;
    private String category;

    
}
