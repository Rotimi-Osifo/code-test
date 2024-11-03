package org.iop.code_test_vaadin.repository;

import org.iop.code_test_vaadin.entity.FruitBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FruitBasketRepository  extends JpaRepository<FruitBasket, Long> {
    Collection<FruitBasket> findByStandId(Long standId);
}
