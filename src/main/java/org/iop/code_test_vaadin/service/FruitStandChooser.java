package org.iop.code_test_vaadin.service;

import org.iop.code_test_vaadin.entity.FruitStand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class FruitStandChooser {
    private static final Logger log = LoggerFactory.getLogger(FruitStandChooser.class);
    private final BasketsPriceCalculator calculator;

    @Autowired
    public FruitStandChooser(BasketsPriceCalculator calculator) {
        this.calculator = calculator;
    }

    public FruitStand ChooseFruitStandWithLowestTotalPrice(Collection<FruitStand> stands) {
        float minTotalPrice = Float.MAX_VALUE;
        FruitStand selectedStand = null;
        for(var stand: stands) {
            float totalPrice = calculator.calculateTotalPrice(stand.getBaskets());
            if(totalPrice < minTotalPrice) {
                minTotalPrice = totalPrice;
                selectedStand = stand;
            }
        }
        if(selectedStand != null) {
            log.info("Selected Fruit Stand: ");
            log.info(selectedStand.toString());
        }
        return selectedStand;
    }

    public FruitStand ChooseFruitStandWithLowestTotalPrice(Collection<FruitStand> stands, Collection<String> selectedBasketNames) {
        float minTotalPrice = Float.MAX_VALUE;
        FruitStand selectedStand = null;
        for(var stand: stands) {
            var baskets = stand.getBasketByNamesList(selectedBasketNames);
            float totalPrice = calculator.calculateTotalPrice(baskets);
            if(totalPrice < minTotalPrice) {
                minTotalPrice = totalPrice;
                selectedStand = stand;
            }
        }
        if(selectedStand != null) {
            log.info("Selected Fruit Stand: ");
            log.info(selectedStand.toString());
        }
        return selectedStand;
    }
}
