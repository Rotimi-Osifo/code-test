package org.iop.code_test_vaadin.DTO;


import lombok.Data;
import org.iop.code_test_vaadin.entity.FruitStand;
@Data
public class FruitBasketDTO {
    private String standName;

    private String fruitBasketName;

    private float price;

    public FruitBasketDTO(String standName, String fruitName, float price) {
        this.standName = standName;
        this.fruitBasketName = fruitName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stand Name =  " +
                standName +
                ": \n" +
                "Fruit name =  " +
                fruitBasketName +
                ", " +
                "Basket price =  " +
                price +
                " \n";
    }
}
