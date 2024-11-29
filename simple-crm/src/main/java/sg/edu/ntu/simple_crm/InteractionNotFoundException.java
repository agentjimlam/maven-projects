package sg.edu.ntu.simple_crm;

public class InteractionNotFoundException extends RuntimeException{
    InteractionNotFoundException (Long id) {
        super("Could not find interaction with id: " + id + ".");
    }
}
