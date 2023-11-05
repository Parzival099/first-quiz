package org.velezreyes.quiz.question6;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {
    private Map<String, Integer> drinkPrices = new HashMap<>();
    private Map<String, Drink> availableDrinks = new HashMap<>();
    private int insertedMoney = 0;

    private VendingMachineImpl() {
        // Inicializa los precios de las bebidas y las bebidas disponibles
        drinkPrices.put("ScottCola", 75);
        drinkPrices.put("KarenTea", 100);
        availableDrinks.put("ScottCola", new DrinkImpl("ScottCola", true));
        availableDrinks.put("KarenTea", new DrinkImpl("KarenTea", false));
    }

    public static VendingMachine getInstance() {
        return new VendingMachineImpl();
    }

    @Override
    public void insertQuarter() {
        insertedMoney += 25;
    }

    @Override
    public Drink pressButton(String drinkName) throws UnknownDrinkException, NotEnoughMoneyException {
        if (!drinkPrices.containsKey(drinkName)) {
            throw new UnknownDrinkException();
        }

        int price = drinkPrices.get(drinkName);
        if (insertedMoney >= price) {
            Drink drink = availableDrinks.get(drinkName);
            if (drink != null) {
                insertedMoney -= price;
                return drink;
            } else {
                throw new UnknownDrinkException();
            }
        } else {
            throw new NotEnoughMoneyException();
        }
    }
}
