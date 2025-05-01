package mk.ukim.finki.petstore.web.rest;

import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.service.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetRestController {
    private final PetService petService;

    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping()
    public List<Pet> getAll() {
        //TODO: ERROR
        return petService.listPets();
    }


    @GetMapping("/generate")
    public List<Pet> getPets() {
//        works
        return petService.createPets();
    }
}
