package mk.ukim.finki.petstore.functionalities.constans;

import java.util.HashMap;

public class Constants {
    public static HashMap<String, Double> currenciesRate;

    public Constants() {
        currenciesRate = new HashMap<>();
        currenciesRate.put("EUR", 61.0);
        currenciesRate.put("USD", 62.0);
        currenciesRate.put("MKD", 1.0);
    }

    public Constants(HashMap<String, Double> currenciesRate) {
        this.currenciesRate = currenciesRate;
    }

    public static HashMap<String, Double> getCurrenciesRate() {
        return currenciesRate;
    }
}
