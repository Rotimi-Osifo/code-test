package org.iop.code_test_vaadin.service;

import org.iop.code_test_vaadin.entity.FruitBasket;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.factory.FruitBasketFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FruitBasketPriceCalculatorTests {
    @Test
    void TestCalculateTotalPrice() {
        float priceForCherriesBasket = 22.10f;
        float priceForPeachesBasket = 22.10f;

        String cherryFruit = "Cherry";
        String peachFruit = "Peach";
        FruitBasket cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        FruitBasket peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        FruitStand fruitStand = new FruitStand("test stand", 1L);
        fruitStand.addFruitBasket(cherries);
        fruitStand.addFruitBasket(peaches);
        cherries.setStand(fruitStand);
        peaches.setStand(fruitStand);

        float totalPrice = priceForCherriesBasket + priceForPeachesBasket;
        BasketsPriceCalculator calculator = new BasketsPriceCalculator();
        float calculatedPrice = calculator.calculateTotalPrice(fruitStand.getBaskets());
        Assertions.assertTrue(Math.abs(totalPrice - calculatedPrice) <= 1E-16);
    }
}