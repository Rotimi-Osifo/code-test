package org.iop.code_test_vaadin.service;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.repository.FruitStandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class FruitStandService {

    private final FruitStandRepository fruitStandRepository;

    @Autowired
    public FruitStandService(FruitStandRepository fruitStandRepository) {
        this.fruitStandRepository = fruitStandRepository;
    }

    public FruitStand save(FruitStand fruitStand) {
        return fruitStandRepository.save(fruitStand);
    }

    public FruitStand getBasketById(long standId) {
        Optional<FruitStand> op = this.fruitStandRepository.findById(standId);
        return op.orElse(null);
    }

    public Collection<FruitStand> findAll() {
        return fruitStandRepository.findAll();
    }

    public Collection<FruitStand> GetFruitStandsWithList(Collection<String> queryStrings){
        Collection<FruitStand> stands = new ArrayList<>();
        var all = fruitStandRepository.findAll();
        for(var stand: all) {
            if(stand.ContainsAllBaskets(queryStrings)) {
                stands.add(stand);
            }
        }
        return stands;
    }

}
