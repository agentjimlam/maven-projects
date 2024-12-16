package sg.edu.ntu.simple_crm.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException (String id) {
        super("Could not find product with id: " + id + ".");
    }
}
