package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public interface InteractionService {
    Interaction saveInteraction(Interaction Interaction);
    Interaction getInteraction(Long id);
    ArrayList<Interaction> getAllInteractions();

    Interaction updateInteraction (Long id, Interaction interaction);

    void deleteInteraction(Long id);
}
