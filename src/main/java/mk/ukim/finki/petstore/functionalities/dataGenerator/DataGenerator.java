package mk.ukim.finki.petstore.functionalities.dataGenerator;
import com.github.javafaker.Faker;
import mk.ukim.finki.petstore.model.*;
import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.model.embeddable.Money;
import mk.ukim.finki.petstore.model.embeddable.Rating;
import mk.ukim.finki.petstore.model.enumerations.Currency;
import mk.ukim.finki.petstore.model.enumerations.Type;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DataGenerator {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static List<Pet> generatePets(int number) {
        List<Pet> pets = new ArrayList<>();
        IntStream.range(0, number).forEach(i -> {
            int num = random.nextInt(2);
            if (num == 0)
                pets.add(generateCat());
            else
                pets.add(generateDog());
        });
        return pets;
    }

    public static List<User> generateUsers(int number) {
        List<User> users = new ArrayList<>();
        IntStream.range(0, number).forEach(i -> users.add(generateUser()));

        return users;
    }
    private static User generateUser(){
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        double money = random.nextDouble(25.0);
        Currency randomCurrency = Currency.values()[random.nextInt(3)];
        if(randomCurrency == Currency.MKD){
            money = money * 65;
        }
        Money balance = new Money(money, randomCurrency);

        return new User(firstName, lastName, email, balance);
    }

    private static Cat generateCat(){
        String name = faker.cat().name();
        String description = faker.lorem().sentence();
        Date dob = generateRandomDOB(1, 20);
        Cat cat = new Cat(name, Type.CAT, description, dob);
        cat.setOwner(null);
        return cat;
    }

    private static Dog generateDog(){
        String name = faker.dog().name();
        String description = faker.lorem().sentence();
        Date dob = generateRandomDOB(1, 15);
        int rating = random.nextInt(11);
        Rating ratingObj = new Rating(rating);
        Dog dog = new Dog(name, Type.DOG, description, dob, ratingObj);
        dog.setOwner(null);
        return dog;
    }

    private static Date generateRandomDOB(int minAge, int maxAge){
        int age = minAge + random.nextInt(maxAge-minAge + 1);
        LocalDate localDate = LocalDate.now()
                .minusYears(age)
                .minusDays(random.nextInt(365));
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
