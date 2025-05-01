package mk.ukim.finki.petstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.petstore.functionalities.calculator.Calculator;
import mk.ukim.finki.petstore.model.embeddable.Money;
import mk.ukim.finki.petstore.model.exceptions.PetHasOwnerException;
import mk.ukim.finki.petstore.model.exceptions.UserLowBudgetException;
import mk.ukim.finki.petstore.model.abstractions.Pet;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "pet_owners")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    @Embedded
    Money money;
    @ElementCollection
    @Column(nullable = false)
    List<Long> petIds;

    public User(String firstName, String lastName, String email, Money money) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.money = money;
        petIds = new ArrayList<>();
    }

    public User() {

    }

    public Pet buyPet(Pet pet) throws UserLowBudgetException, PetHasOwnerException {
        Money petPrice = pet.getPrice();
        if (Calculator.isGreaterThan(petPrice, money))
            throw new UserLowBudgetException(this.firstName, pet.getName(), pet.getPrice().getAmount());
        if(pet.getOwner() != null)
            throw new PetHasOwnerException(this.firstName, pet.getName());
        pet.setOwner(this);
        this.buyPetBalance(pet);
        petIds.add(pet.getId());
        return pet;
    }

    private void buyPetBalance(Pet pet) {
        Money petPrice = pet.getPrice();
        this.money = Calculator.calculateBalance(money, petPrice);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

}
