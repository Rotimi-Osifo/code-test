package org.iop.code_test_vaadin.model;

import org.iop.code_test_vaadin.entity.FruitBasket;
import org.iop.code_test_vaadin.factory.FruitBasketFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FruitBasketTests {
    @Test
    public void TestCreateFruitBasket() {
        float price = 20.22f;
        String fruitName = "Cherry";
        FruitBasket basket = FruitBasketFactory.createFruitBasket(fruitName, price);
        Assertions.assertNotNull(basket);
        Assertions.assertEquals(price, basket.getPrice());
        Assertions.assertEquals(fruitName , basket.getFruitName());
    }
}