package mk.ukim.finki.petstore.model.exceptions;

public class PetHasOwnerException extends Exception{

    public PetHasOwnerException(String user, String pet) {
        super("User: " + user + " can't buy pet: " + pet + "; " + pet + " has owner" );
    }
}
