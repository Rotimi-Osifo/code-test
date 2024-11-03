package org.iop.code_test_vaadin.service;

import org.iop.code_test_vaadin.entity.FruitBasket;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.factory.FruitBasketFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FruitStandChooserTests {
    @Test
    void TestChooseFruitStandWithLowestTotalPrice() {
        final Map<Long, FruitStand> stands = new HashMap<Long, FruitStand>();

        float priceForCherriesBasket = 22.10f;
        float priceForPeachesBasket = 22.10f;

        // fruit stand 4
        String cherryFruit = "Cherry";
        String peachFruit = "Peach";

        FruitBasket cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        FruitBasket peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        FruitStand fruitStand = new FruitStand("fourth",4L);
        fruitStand.addFruitBasket(cherries);
        fruitStand.addFruitBasket(peaches);
        cherries.setStand(fruitStand);
        peaches.setStand(fruitStand);
        stands.put(4L, fruitStand);

        // fruit stand 1
        priceForCherriesBasket = 20.10f;
        priceForPeachesBasket = 15.15f;

        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        fruitStand = new FruitStand("first", 1L);
        fruitStand.addFruitBasket(cherries);
        fruitStand.addFruitBasket(peaches);
        cherries.setStand(fruitStand);
        peaches.setStand(fruitStand);
        stands.put(1L, fruitStand);

        // fruit stand 2
        priceForCherriesBasket = 20.11f;
        priceForPeachesBasket = 16.15f;

        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        fruitStand = new FruitStand("second",2L);
        fruitStand.addFruitBasket(cherries);
        fruitStand.addFruitBasket(peaches);
        cherries.setStand(fruitStand);
        peaches.setStand(fruitStand);
        stands.put(2L, fruitStand);

        // fruit stand 5
        priceForCherriesBasket = 22.11f;
        priceForPeachesBasket = 17.20f;

        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        fruitStand = new FruitStand("fifth",5L);
        fruitStand.addFruitBasket(cherries);
        fruitStand.addFruitBasket(peaches);
        cherries.setStand(fruitStand);
        peaches.setStand(fruitStand);
        stands.put(5L, fruitStand);

        // fruit stand 3
        priceForCherriesBasket = 22.35f;
        priceForPeachesBasket = 15.99f;

        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        fruitStand = new FruitStand("third",3L);
        fruitStand.addFruitBasket(cherries);
        fruitStand.addFruitBasket(peaches);
        cherries.setStand(fruitStand);
        peaches.setStand(fruitStand);
        stands.put(3L, fruitStand);

        BasketsPriceCalculator calculator = new BasketsPriceCalculator();
        FruitStandChooser chooser = new FruitStandChooser(calculator);
        FruitStand selectedStand = chooser.ChooseFruitStandWithLowestTotalPrice(stands.values());
        Long expected = 1L;
        Assertions.assertEquals(expected, selectedStand.getId());
    }
}