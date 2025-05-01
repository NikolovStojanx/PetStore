package mk.ukim.finki.petstore.model.embeddable;

import jakarta.persistence.*;

@Embeddable
public class Rating {
    private int rating;

    public Rating(int rating) {
        this.rating = rating;
    }

    public Rating() {

    }

    public int getRating() {
        return rating;
    }
}
