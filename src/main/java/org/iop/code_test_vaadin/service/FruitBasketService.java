package org.iop.code_test_vaadin.service;

import org.iop.code_test_vaadin.entity.FruitBasket;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.repository.FruitBasketRepository;
import org.iop.code_test_vaadin.repository.FruitStandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FruitBasketService {
    private final FruitBasketRepository fruitBasketRepository;

    public final FruitStandRepository fruitStandRepository;
    @Autowired
    public FruitBasketService(FruitBasketRepository fruitBasketRepository, FruitStandRepository fruitStandRepository) {
        this.fruitBasketRepository = fruitBasketRepository;
        this.fruitStandRepository = fruitStandRepository;
    }

    public FruitBasket saveBasket(FruitBasket fruitBasket, long standId) {
        Optional<FruitStand> op = this.fruitStandRepository.findById(standId);
        if(op.isPresent()) {
            FruitStand stand = op.get();
            fruitBasket.setStand(stand);
            var savedBasket = this.fruitBasketRepository.save(fruitBasket);

            stand.addFruitBasket(fruitBasket);
            this.fruitStandRepository.save(stand);

            return savedBasket;
        }
        return null;
    }

    public FruitBasket getBasketById(long basketId) {
        Optional<FruitBasket>op = this.fruitBasketRepository.findById(basketId);
        return op.orElse(null);
    }

    public Collection<FruitBasket> getBasketsForStand(long standId) {
        return this.fruitBasketRepository.findByStandId(standId);
    }
}
