package sg.edu.ntu.simple_crm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    
}
