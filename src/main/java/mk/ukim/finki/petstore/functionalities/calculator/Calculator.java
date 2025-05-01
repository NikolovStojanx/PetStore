package mk.ukim.finki.petstore.functionalities.calculator;

import jakarta.persistence.Embeddable;
import mk.ukim.finki.petstore.functionalities.constans.Constants;
import mk.ukim.finki.petstore.model.embeddable.Money;
import mk.ukim.finki.petstore.model.enumerations.Currency;

import java.util.HashMap;

public class Calculator {
    static public Constants constants = new Constants();


    public static Money calculateBalance(Money money, Money minusMoney) {
//        convert both currencies to mkd
        double moneyMkd = toMkd(money);
        double minusMoneyMkd = toMkd(minusMoney);

//        calculate the operations
//        result in mkd
        double newBalanceMkd = moneyMkd - minusMoneyMkd;
//        convert mkd to the currency of money
        Money newBalanceMoneyMkd = new Money(newBalanceMkd, Currency.MKD);
        double moneyBalancedCurr = convertMoneyFromMkdToCurr(newBalanceMoneyMkd, money.getCurrency());
        return new Money(moneyBalancedCurr, money.getCurrency());
    }

    public static boolean isGreaterThan(Money m1, Money m2) {
        return toMkd(m1) > toMkd(m2);
    }

    private static double toMkd(Money money) {
        HashMap<String, Double> rates = constants.getCurrenciesRate();
        String currency = money.getCurrency().name();
        Double rate = rates.get(currency);
        return money.getAmount() * rate;
    }
    private static double convertMoneyFromMkdToCurr(Money money, Currency currency) {
        double rate = constants.getCurrenciesRate().get(currency.name());
        return money.getAmount() / rate;
    }

    public static void main(String[] args) {
        Money money = new Money(6200.0, Currency.MKD);
        Money minusMoney = new Money(50.0, Currency.USD);
        money.subtract(minusMoney);
        System.out.println(calculateBalance(money, minusMoney).getAmount());
    }

}
