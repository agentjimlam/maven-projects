package sg.edu.ntu.simple_crm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl implements InteractionService {
    private InteractionRepository interactionRepository;

    // Constructor injection
    public InteractionServiceImpl(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    @Override
    public Interaction saveInteraction(Interaction interaction) {
        Interaction newInteraction = interactionRepository.save(interaction);
        return newInteraction;
    }

    @Override
    public Interaction getInteraction(Long id) {
        // Interaction foundInteraction = interactionRepository.findById(id).get();
        // return foundInteraction;

        return interactionRepository.findById(id).orElseThrow(()-> new InteractionNotFoundException(id));
    }

    @Override
    public ArrayList<Interaction> getAllInteractions() {
        List<Interaction> allInteractions = interactionRepository.findAll();
        return (ArrayList<Interaction>) allInteractions;
        //ArrayList<Interaction> allInteractions = (ArrayList<Interaction>) interactionRepository.findAll();
        //return allInteractions;
    }

    @Override
    public Interaction updateInteraction(Long id, Interaction interaction) {
        // Retrieve the customer from db
        // Interaction interactionToUpdate = interactionRepository.findById(id).get();
        Interaction interactionToUpdate = interactionRepository.findById(id).orElseThrow(()-> new InteractionNotFoundException(id));
        // Update the customer object that was retrieved
        interactionToUpdate.setRemarks(interaction.getRemarks());
        interactionToUpdate.setInteractionDate(interaction.getInteractionDate());

        // Save updated customer back to db
        return interactionRepository.save(interactionToUpdate);
    }

    @Override
    public void deleteInteraction(Long id) {
        interactionRepository.deleteById(id);
    }
}
