package mk.ukim.finki.petstore.service;

import mk.ukim.finki.petstore.model.User;
import mk.ukim.finki.petstore.model.abstractions.Pet;

import java.util.List;

public interface PetService {
//    create max 20 pets of different kinds
    List<Pet> createPets();
    List<Pet> listPets();

    void save(Pet pet);
    Pet findPetById(Long id);
}
