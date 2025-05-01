package mk.ukim.finki.petstore;


import mk.ukim.finki.petstore.functionalities.calculator.Calculator;
import mk.ukim.finki.petstore.functionalities.constans.Constants;
import mk.ukim.finki.petstore.model.embeddable.Money;
import mk.ukim.finki.petstore.model.enumerations.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;
    private static final double DELTA = 0.001; // For double comparison

    @BeforeEach
    void setUp() {
        // Initialize with default currency rates
        calculator = new Calculator();
    }

    @Test
    void testCalculateBalance_SameCurrency_MKD() {
        // Both amounts in MKD
        Money money = new Money(1000.0, Currency.MKD);
        Money minusMoney = new Money(300.0, Currency.MKD);

        Money result = Calculator.calculateBalance(money, minusMoney);

        assertEquals(700.0, result.getAmount(), DELTA);
        assertEquals(Currency.MKD, result.getCurrency());
    }

    @Test
    void testCalculateBalance_SameCurrency_EUR() {
        // Both amounts in EUR
        Money money = new Money(100.0, Currency.EUR);
        Money minusMoney = new Money(30.0, Currency.EUR);

        Money result = Calculator.calculateBalance(money, minusMoney);

        assertEquals(70.0, result.getAmount(), DELTA);
        assertEquals(Currency.EUR, result.getCurrency());
    }

    @Test
    void testCalculateBalance_SameCurrency_USD() {
        // Both amounts in USD
        Money money = new Money(200.0, Currency.USD);
        Money minusMoney = new Money(50.0, Currency.USD);

        Money result = Calculator.calculateBalance(money, minusMoney);

        assertEquals(150.0, result.getAmount(), DELTA);
        assertEquals(Currency.USD, result.getCurrency());
    }

    @Test
    void testCalculateBalance_DifferentCurrencies_EUR_to_MKD() {
        // EUR and MKD
        Money money = new Money(100.0, Currency.EUR);  // 100 EUR = 6100 MKD
        Money minusMoney = new Money(1000.0, Currency.MKD); // 1000 MKD

        Money result = Calculator.calculateBalance(money, minusMoney);

        // Expected: (100 * 61 - 1000) / 61 = 5100 / 61 = 83.61 EUR
        assertEquals(83.607, result.getAmount(), DELTA);
        assertEquals(Currency.EUR, result.getCurrency());
    }

    @Test
    void testCalculateBalance_DifferentCurrencies_USD_to_EUR() {
        // USD and EUR
        Money money = new Money(100.0, Currency.USD);  // 100 USD = 6200 MKD
        Money minusMoney = new Money(50.0, Currency.EUR); // 50 EUR = 3050 MKD

        Money result = Calculator.calculateBalance(money, minusMoney);

        // Expected: (100 * 62 - 50 * 61) / 62 = (6200 - 3050) / 62 = 3150 / 62 = 50.81 USD
        assertEquals(50.806, result.getAmount(), DELTA);
        assertEquals(Currency.USD, result.getCurrency());
    }

    @Test
    void testCalculateBalance_DifferentCurrencies_MKD_to_USD() {
        // MKD and USD
        Money money = new Money(6200.0, Currency.MKD);  // 6200 MKD
        Money minusMoney = new Money(50.0, Currency.USD); // 50 USD = 3100 MKD

        Money result = Calculator.calculateBalance(money, minusMoney);

        // Expected: (6200 - 50 * 62) / 1 = 6200 - 3100 = 3100 MKD
        assertEquals(3100.0, result.getAmount(), DELTA);
        assertEquals(Currency.MKD, result.getCurrency());
    }

    @Test
    void testIsGreaterThan_SameCurrency() {
        Money m1 = new Money(100.0, Currency.EUR);
        Money m2 = new Money(90.0, Currency.EUR);
        Money m3 = new Money(110.0, Currency.EUR);

        assertTrue(Calculator.isGreaterThan(m1, m2));
        assertFalse(Calculator.isGreaterThan(m1, m3));
    }

    @Test
    void testIsGreaterThan_DifferentCurrencies() {
        // 100 EUR = 6100 MKD, 90 USD = 5580 MKD
        Money m1 = new Money(100.0, Currency.EUR);
        Money m2 = new Money(90.0, Currency.USD);
        assertTrue(Calculator.isGreaterThan(m1, m2));

        // 50 EUR = 3050 MKD, 55 USD = 3410 MKD
        Money m3 = new Money(50.0, Currency.EUR);
        Money m4 = new Money(55.0, Currency.USD);
        assertFalse(Calculator.isGreaterThan(m3, m4));
    }

    @Test
    void testIsGreaterThan_WithMKD() {
        // 61 EUR = 3721 MKD, 3700 MKD
        Money m1 = new Money(61.0, Currency.EUR);
        Money m2 = new Money(3700.0, Currency.MKD);
        assertTrue(Calculator.isGreaterThan(m1, m2));

        // 45 USD = 2790 MKD, 2800 MKD
        Money m3 = new Money(45.0, Currency.USD);
        Money m4 = new Money(2800.0, Currency.MKD);
        assertFalse(Calculator.isGreaterThan(m3, m4));
    }

    @Test
    void testCalculateBalance_NegativeResult() {
        // Test when result will be negative
        Money money = new Money(50.0, Currency.EUR);  // 50 EUR = 3050 MKD
        Money minusMoney = new Money(100.0, Currency.EUR); // 100 EUR = 6100 MKD

        Money result = Calculator.calculateBalance(money, minusMoney);

        // Expected: (50 * 61 - 100 * 61) / 61 = -50 EUR
        assertEquals(-50.0, result.getAmount(), DELTA);
        assertEquals(Currency.EUR, result.getCurrency());
    }

    @Test
    void testCalculateBalance_WithCustomRates() {
        // Use a temporary custom rate
        HashMap<String, Double> customRates = new HashMap<>();
        customRates.put("EUR", 70.0);
        customRates.put("USD", 65.0);
        customRates.put("MKD", 1.0);

        Constants originalConstants = Calculator.constants;
        Calculator.constants = new Constants(customRates);

        try {
            Money money = new Money(100.0, Currency.EUR);  // 100 EUR = 7000 MKD with custom rate
            Money minusMoney = new Money(50.0, Currency.USD); // 50 USD = 3250 MKD with custom rate

            Money result = Calculator.calculateBalance(money, minusMoney);

            // Expected: (100 * 70 - 50 * 65) / 70 = (7000 - 3250) / 70 = 53.57 EUR
            assertEquals(53.571, result.getAmount(), DELTA);
            assertEquals(Currency.EUR, result.getCurrency());
        } finally {
            // Restore original constants
            Calculator.constants = originalConstants;
        }
    }

    @Test
    void testCalculateBalance_ZeroValues() {
        // Test with zero values
        Money money = new Money(100.0, Currency.EUR);
        Money minusMoney = new Money(0.0, Currency.USD);

        Money result = Calculator.calculateBalance(money, minusMoney);

        assertEquals(100.0, result.getAmount(), DELTA);
        assertEquals(Currency.EUR, result.getCurrency());

        // Zero minus something
        Money zeroMoney = new Money(0.0, Currency.EUR);
        Money someValue = new Money(50.0, Currency.USD);

        Money zeroResult = Calculator.calculateBalance(zeroMoney, someValue);

        // Expected: (0 * 61 - 50 * 62) / 61 = -50.82 EUR
        assertEquals(-50.82, zeroResult.getAmount(), DELTA);
        assertEquals(Currency.EUR, zeroResult.getCurrency());
    }
}