package sg.edu.ntu.simple_crm;

// Unchecked exception
public class ExpenseNotFoundException extends RuntimeException{
    ExpenseNotFoundException (String id){
        super("Could not find Expense with id: " + id + ".");
    }
}
