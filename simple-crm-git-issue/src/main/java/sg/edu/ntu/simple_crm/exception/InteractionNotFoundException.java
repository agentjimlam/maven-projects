package sg.edu.ntu.simple_crm.exception;

public class InteractionNotFoundException extends RuntimeException{
    public InteractionNotFoundException (Long id) {
        super("Could not find interaction with id: " + id + ".");
    }
}
