package mk.ukim.finki.petstore.service.impl;

import mk.ukim.finki.petstore.functionalities.dataGenerator.DataGenerator;
import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.repository.jpa.PetRepository;
import mk.ukim.finki.petstore.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> createPets() {
        List<Pet> petsToSave =  DataGenerator.generatePets(20);
        petRepository.saveAll(petsToSave);
        return petsToSave;
    }

    @Override
    public List<Pet> listPets() {
        return petRepository.findAll();
    }

    @Override
    public void save(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public Pet findPetById(Long id) {
        return petRepository.findById(id).get();
    }
}
