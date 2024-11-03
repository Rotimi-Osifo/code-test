package org.iop.code_test_vaadin;

import org.iop.code_test_vaadin.entity.FruitBasket;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.factory.FruitBasketFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class FruitStandTests {

    @Test
    public void TestCreateFruitStand() {
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

        Collection<FruitBasket> baskets = fruitStand.getBaskets();
        Assertions.assertEquals(baskets.size(), 2);
        for (var basket : baskets) {
            switch(basket.getFruitName()) {
                case "Cherry":
                    Assertions.assertEquals(priceForCherriesBasket, basket.getPrice());
                    break;
                case "Peach":
                    Assertions.assertEquals(priceForPeachesBasket, basket.getPrice());
                    break;
                default:
                    break;
            }
        }
    }
}