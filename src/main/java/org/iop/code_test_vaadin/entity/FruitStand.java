package org.iop.code_test_vaadin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="stand")
@Data
public final class FruitStand {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "stand", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private Collection<FruitBasket> baskets = new ArrayList<>();
    public void addBasket(FruitBasket basket) {
        this.baskets.add(basket);
    }
    public FruitStand() {}

    public FruitStand(String name, long id) {
        this.id = id;
        this.name = name;
    }

    public FruitStand(String name) {
        this.name = name;
    }

    public void addFruitBasket(String fruitName, float price) {
        FruitBasket basket = new FruitBasket(fruitName, price);
        baskets.add(basket);
    }

    public void addFruitBasket(FruitBasket basket) {
        baskets.add(basket);
    }

    public boolean ContainsAllBaskets(Collection<String> fruitNames) {
        int count = 0;
        for(var basket : baskets) {
            for(var name : fruitNames) {
                if(name.equals(basket.getFruitName())) { //strict equality
                    count++;
                }
            }
        }
        return count == fruitNames.size();
    }

    public Collection<FruitBasket> getBasketByNamesList(Collection<String> namesList) {
        Collection<FruitBasket> basketsLoc = new ArrayList<>();
        for(var basket : baskets) {
            if(namesList.contains(basket.getFruitName())) {
                basketsLoc.add(basket);
            }
        }
        return  basketsLoc;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stand Id =  ");
        builder.append(id);
        builder.append( ": \n");
        builder.append("Stand Name =  ");
        builder.append(name);
        builder.append( ": \n");
        for(var basket : baskets) {
            builder.append("Basket Id =  ");
            builder.append(basket.getId());
            builder.append(", ");
            builder.append("Fruit name =  ");
            builder.append(basket.getFruitName());
            builder.append(", ");
            builder.append("Basket price =  ");
            builder.append(basket.getPrice());
            builder.append(" \n");
        }
        return builder.toString();
    }
}
