package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("expenses")
public class ExpenseController {
    private ArrayList<Expense> expenses = new ArrayList<>();

    public ExpenseController() {
        expenses.add(new Expense("Fruits", 100.00, "grocery"));
        expenses.add(new Expense("Petrol", 300.00, "transportation"));
        expenses.add(new Expense("Taxi", 25.00, "transportation"));
        expenses.add(new Expense("Grab", 15.00, "transportation"));
        expenses.add(new Expense("Hamburger", 50.00, "food"));
        expenses.add(new Expense("iPad", 999.00, "tech"));
        expenses.add(new Expense("iPhone", 1999.00, "tech"));
    }

    // Create an expense
    @PostMapping("")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        expenses.add(expense);

        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }
    
    // Read - get all expenses
    @GetMapping("")
    public ResponseEntity<ArrayList<Expense>> getAllExpenses(@RequestParam(required = false) String category,
        @RequestParam(required = false) Double minAmount, @RequestParam(required = false) Double maxAmount) {

        // If user specifies minAmount more than maxAmount, return a 400 Bad Request
        if (minAmount != null && maxAmount != null && minAmount > maxAmount) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        // Create a copy of expenses ArrayList
        ArrayList<Expense> expensesCopy = new ArrayList<>(expenses);
        
        // if user specifies category, filter the expenses by category
        if(category != null){
            // Removes all expenses that do not match the category
            expensesCopy.removeIf(expense -> !expense.getCategory().equals(category));
        }

        // if user specifies minAmount, filter the expenses by minAmount
        if(minAmount != null){
            // Removes all expenses that have amount less than minAmount
            expensesCopy.removeIf(expense -> expense.getAmount() < minAmount);
        }

        // If user specifies maxAmount, filter the expenses by maxAmount
        if(maxAmount != null){
            // Removes all expenses that have amount more than maxAmount
            expensesCopy.removeIf(expenses -> expenses.getAmount() > maxAmount);
        }

        return new ResponseEntity<>(expensesCopy, HttpStatus.OK);
    }

    // Read - get one expense
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable String id) {
        try {
            int index = getExpenseIndex(id);
            return new ResponseEntity<>(expenses.get(index), HttpStatus.OK);
        } catch (ExpenseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable String id, @RequestBody Expense expense) {
        try {
            expenses.set(getExpenseIndex(id), expense);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (ExpenseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable String id){
        expenses.remove(getExpenseIndex(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


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
