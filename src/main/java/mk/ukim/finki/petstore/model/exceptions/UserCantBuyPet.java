package mk.ukim.finki.petstore.model.exceptions;

public class UserCantBuyPet extends Exception {
    public UserCantBuyPet(String user) {
        super(user + " cannot buy pet.");
    }
}
