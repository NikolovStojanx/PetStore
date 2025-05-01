package mk.ukim.finki.petstore.model.dto;

import mk.ukim.finki.petstore.model.abstractions.Pet;

import java.util.List;

public class UserDTO {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public List<String> pets;

    public UserDTO(Long id, String firstName, String lastName, String email, List<String> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pets = pets;
    }

    public UserDTO() {
    }


}
