package sg.edu.ntu.simple_crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.simple_crm.entity.Interaction;

@Service
public interface InteractionService {
    Interaction saveInteraction(Interaction Interaction);
    Interaction getInteraction(Long id);
    List<Interaction> getAllInteractions();

    Interaction updateInteraction (Long id, Interaction interaction);

    void deleteInteraction(Long id);
}
