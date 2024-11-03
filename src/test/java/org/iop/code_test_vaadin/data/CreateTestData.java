package org.iop.code_test_vaadin.data;

import org.iop.code_test_vaadin.entity.FruitBasket;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.factory.FruitBasketFactory;

public class CreateTestData {

    public static FruitStand createStand() {
        return new FruitStand("One_And_Only");
    }

    public static FruitBasket createBasket() {
        float price = 20.22f;
        String fruitName = "Cherry";
        return FruitBasketFactory.createFruitBasket(fruitName, price);
    }

}
