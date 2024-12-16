package sg.edu.ntu.simple_crm;

import java.time.LocalDateTime;
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
    private LocalDateTime dateTime;

    public Expense (String description, Double amount, String category){
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.dateTime = LocalDateTime.now();
    }
}
