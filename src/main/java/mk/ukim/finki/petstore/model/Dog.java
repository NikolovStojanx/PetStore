package mk.ukim.finki.petstore.model;

import jakarta.persistence.*;
import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.model.enumerations.Type;

import java.util.Date;

@Entity
public class Dog extends Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Rating rating;
    public Dog(User owner, String name, Type type, String description, Date dateOfBirth, Price money, Rating rating) {
        super(name, type, description, dateOfBirth);
        this.rating = rating;
    }

    public Dog() {

    }

    @Override
    public Price getPrice() {
        int age = this.getAge();
        return new Price(age + rating.getRating());
    }

    @Override
    public String petSuccessfullyBoughtMessage() {
        return "Woof, dog " + this.getName() + " has owner " + this.getOwner().get();
    }
}
