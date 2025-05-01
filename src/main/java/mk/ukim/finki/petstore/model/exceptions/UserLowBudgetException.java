package mk.ukim.finki.petstore.model.exceptions;

public class UserLowBudgetException extends Exception {
    public UserLowBudgetException(String user, String pet, double petPrice) {
        super(user + " doesn't have the budget for " + pet + " of " + petPrice);
    }
}
