package sg.edu.ntu.simple_crm;

public class CustomerNotFoundException extends RuntimeException {
    CustomerNotFoundException (Long id){
        super("Could not find customer with id: " + id + ".");
    }
}
