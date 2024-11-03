package org.iop.code_test_vaadin.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="basket")
@Data
public final class FruitBasket {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String fruitName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stand_id", nullable = false)
    private FruitStand stand;

    @Column(name = "price", nullable = false)
    private float price;

    @Override
    public String toString() {
        String builder = "Stand Id =  " +
                stand.getId() +
                ": \n" +
                "Stand Name =  " +
                stand.getName() +
                ": \n" +
                "Basket Id =  " +
                id +
                ", " +
                "Fruit name =  " +
                fruitName +
                ", " +
                "Basket price =  " +
                price +
                " \n";

        return builder;
    }

    private FruitBasket() {}
    public FruitBasket(String fruitName, float basketPrice) {
        if (basketPrice > 0) {
            this.fruitName = fruitName;
            this.price = basketPrice;
        } else {
            throw new IllegalStateException("Fruit Basket Price must be greater than zero: " + basketPrice);
        }
    }
}
