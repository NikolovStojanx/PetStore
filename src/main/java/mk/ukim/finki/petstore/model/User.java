package mk.ukim.finki.petstore.model;

import jakarta.persistence.*;
import mk.ukim.finki.petstore.exceptions.PetHasOwnerException;
import mk.ukim.finki.petstore.exceptions.UserLowBudgetException;
import mk.ukim.finki.petstore.model.abstractions.Pet;

@Entity
@Table(name = "pet_owners")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String firstName;
    String lastName;
    String email;
    Budget money;

    public User(String firstName, String lastName, String email, Budget money) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.money = money;
    }

    public User() {

    }

    public boolean buyPet(Pet pet) throws UserLowBudgetException, PetHasOwnerException {
        Price petPrice = pet.getPrice();
        if (petPrice.getPrice() > this.money.getBalance())
            throw new UserLowBudgetException(this.firstName, pet.getName(), pet.getPrice().getPrice());
        if(pet.getOwner().isPresent())
            throw new PetHasOwnerException(this.firstName, pet.getName());

        pet.setOwner(this);

        return true;

    }
}
