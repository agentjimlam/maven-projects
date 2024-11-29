package sg.edu.ntu.simple_crm;

public class CustomerNotFoundException extends RuntimeException {
    CustomerNotFoundException (String id){
        super("Could not find customer with id: " + id + ".");
    }
}
