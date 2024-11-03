package org.iop.code_test_vaadin.factory;

import org.iop.code_test_vaadin.entity.FruitBasket;

public class FruitBasketFactory {

    public static FruitBasket createFruitBasket(String fruitName, float basketPrice) {
        return new FruitBasket(fruitName, basketPrice);
    }
}