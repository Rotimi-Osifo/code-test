package org.iop.code_test_vaadin.service;

import org.iop.code_test_vaadin.entity.FruitBasket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BasketsPriceCalculator {
    private static final Logger log = LoggerFactory.getLogger(BasketsPriceCalculator.class);
    public float calculateTotalPrice(Collection<FruitBasket> baskets) {
        float totalPrice = 0.0f;
        for (FruitBasket basket : baskets) {
            log.info(basket.toString());
            totalPrice += basket.getPrice(); //uses lombok
        }
        return totalPrice;
    }
}