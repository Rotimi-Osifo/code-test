package org.iop.code_test_vaadin.repository;

import org.iop.code_test_vaadin.entity.FruitStand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FruitStandRepository   extends JpaRepository<FruitStand, Long> {
}
