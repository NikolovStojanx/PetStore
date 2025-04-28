package mk.ukim.finki.petstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.model.enumerations.Type;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Cat extends Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, updatable = false) 
    private Type type;
    public Cat(String name, Type type, String description, Date dateOfBirth) {
        super(name, type, description, dateOfBirth);
    }

    public Cat() {

    }

    @Override
    public Price getPrice() {
        int age = this.getAge();
        return new Price(age);
    }

    @Override
    public String petSuccessfullyBoughtMessage() {
        return "Meow, cat " + this.getName() + " has owner " + this.getOwner().get();
    }


}
