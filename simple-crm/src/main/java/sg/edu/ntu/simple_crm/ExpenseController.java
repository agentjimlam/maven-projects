package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("expenses")
public class ExpenseController {
    private ArrayList<Expense> expenses = new ArrayList<>();

    public ExpenseController(){
        expenses.add(new Expense("Oranges", 10.50, "grocery"));
    }

    //Create an expense
    @PostMapping("")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        expenses.add(expense);
        
        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }
    
    // Read - get all expenses
    @GetMapping("")
    public ResponseEntity<ArrayList<Expense>> getAllExpenses() {
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // Read - get one expense
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable String id) {
        try {
        int index = getExpenseIndex(id);
        return new ResponseEntity<>(expenses.get(index), HttpStatus.OK);
        } catch (ExpenseNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    //Update 
    
    
    // Helper function
    public int getExpenseIndex(String id) {
        for (Expense expense : expenses) {
            if (expense.getId().equals(id)) {
                return expenses.indexOf(expense);
            }
        }
        throw new ExpenseNotFoundException(id);
    }


}
