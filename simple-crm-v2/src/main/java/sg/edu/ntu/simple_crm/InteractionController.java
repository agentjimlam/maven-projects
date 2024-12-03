package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interactions")
public class InteractionController {

    private InteractionService interactionService;

    // Constructor injector
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    /// Save an interaction
    @PostMapping("")
    public ResponseEntity<Interaction> saveInteraction(@RequestBody Interaction interaction) {
        Interaction newInteraction = interactionService.saveInteraction(interaction);
        // return interaction;
        return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
    }

    // Read - get all customers
    @GetMapping("")
    public ResponseEntity<ArrayList<Interaction>> getAllInteractions() {
        ArrayList<Interaction> allInteractions = interactionService.getAllInteractions();
        return new ResponseEntity<>(allInteractions, HttpStatus.OK);
    }

    // Read - get one interaction
    // localhost:8080/customers/35623130-bade-43d6-9bf4-6ea7189301fb
    @GetMapping("/{id}")
    public ResponseEntity<Interaction> getInteraction(@PathVariable Long id) {
        try {
            Interaction foundInteraction = interactionService.getInteraction(id);
            return new ResponseEntity<>(foundInteraction, HttpStatus.OK);
        } catch (InteractionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }

    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Interaction> updateInteraction(@PathVariable Long id, @RequestBody Interaction interaction) {
        try {
            Interaction updateInteraction = interactionService.updateInteraction(id, interaction);
            return new ResponseEntity<>(updateInteraction, HttpStatus.OK);
        } catch (InteractionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Interaction> deleteInteraction(@PathVariable Long id){
        interactionService.deleteInteraction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}