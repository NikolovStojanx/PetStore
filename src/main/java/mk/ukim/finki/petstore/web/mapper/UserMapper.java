package mk.ukim.finki.petstore.web.mapper;

import mk.ukim.finki.petstore.model.User;
import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.model.dto.UserDTO;
import mk.ukim.finki.petstore.service.PetService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final PetService petService;

    public UserMapper(PetService petService) {
        this.petService = petService;
    }

    public UserDTO toDTO(User user) {
        List<Long> petIds = user.getPetIds();
        List<String> pets = petIds.stream()
                .map(petService::findPetById)
                .map(Pet::getName)
                .collect(Collectors.toList());

        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                pets
        );
    }

}
