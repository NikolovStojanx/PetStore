package mk.ukim.finki.petstore.repository.jpa;

import mk.ukim.finki.petstore.model.abstractions.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
