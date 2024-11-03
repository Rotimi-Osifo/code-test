package org.iop.code_test_vaadin.service;

import jakarta.transaction.Transactional;
import org.iop.code_test_vaadin.DTO.FruitBasketDTO;
import org.iop.code_test_vaadin.entity.FruitBasket;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.factory.FruitBasketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class DataService {
    private static final Logger log = LoggerFactory.getLogger(DataService.class);

    @Autowired
    private final FruitBasketService fruitBasketService;

    @Autowired
    private final FruitStandService fruitStandService;

    @Autowired
    private final FruitStandChooser chooser;

    @Autowired
    public DataService(FruitBasketService fruitBasketService, FruitStandService fruitStandService, FruitStandChooser chooser) {
        this.fruitBasketService = fruitBasketService;
        this.fruitStandService = fruitStandService;
        this.chooser = chooser;
    }

    public void seedDataBase() {
        var stands = fruitStandService.findAll();
        if(stands.isEmpty()) { // There are better ways for this with unique index etc, this will do
            FruitStand stand = new FruitStand("Best Fruits in Town");
            var savedStand = fruitStandService.save(stand);
            float priceForOrangesBasket = 16.10f;
            float priceForPearsBasket = 23.10f;
            String orangeFruit = "Orange";
            String pearFruit = "Pear";
            FruitBasket oranges = FruitBasketFactory.createFruitBasket(orangeFruit,  priceForOrangesBasket);
            var savedOrangesBasket = this.fruitBasketService.saveBasket(oranges, savedStand.getId());
            FruitBasket pears = FruitBasketFactory.createFruitBasket(pearFruit, priceForPearsBasket);
            var savedPearsBasket = this.fruitBasketService.saveBasket(pears, savedStand.getId());

            float priceForApplesBasket = 16.10f;
            float priceForAvocadoBasket = 23.10f;
            String appleFruit = "Apple";
            String avocadoFruit = "Avocado";
            FruitBasket apples = FruitBasketFactory.createFruitBasket(appleFruit,  priceForApplesBasket);
            var savedApplesBasket = this.fruitBasketService.saveBasket(apples, savedStand.getId());
            FruitBasket avocados = FruitBasketFactory.createFruitBasket(avocadoFruit , priceForAvocadoBasket);
            var savedAvocadossBasket = this.fruitBasketService.saveBasket(avocados, savedStand.getId());

            seedDataBaseWithData(); // add 5 more stands

            float priceForCherriesBasket = 19.10f;
            float priceForPeachesBasket = 15.10f;
            String cherryFruit = "Cherry";
            String peachFruit = "Peach";
            FruitBasket cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
            var savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
            FruitBasket peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);
            var savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

            log.info(savedStand.toString());
        } else {

            log.info("Data has already been loaded into the system db!!!");
        }

    }

    private void seedDataBaseWithData() {
        FruitStand stand = new FruitStand("Pristine Fruits");
        var savedStand = fruitStandService.save(stand);

        float priceForCherriesBasket = 22.10f;
        float priceForPeachesBasket = 22.10f;
        String cherryFruit = "Cherry";
        String peachFruit = "Peach";
        FruitBasket cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        var savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        FruitBasket peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);
        var savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        priceForCherriesBasket = 20.11f;
        priceForPeachesBasket = 16.15f;
        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        stand = new FruitStand("classic Fruits");
        savedStand = fruitStandService.save(stand);
        savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        priceForCherriesBasket = 20.10f;
        priceForPeachesBasket = 15.15f;
        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        stand = new FruitStand("Locally Produced Fruits");
        savedStand = fruitStandService.save(stand);
        savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        priceForCherriesBasket = 22.35f;
        priceForPeachesBasket = 15.99f;
        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        stand = new FruitStand("Organic Fruits");
        savedStand = fruitStandService.save(stand);
        savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        priceForCherriesBasket = 22.11f;
        priceForPeachesBasket = 17.20f;
        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        stand = new FruitStand("Naturally Produced Fruits");
        savedStand = fruitStandService.save(stand);
        savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());
    }

    public List<String> BasketNames() {
        Collection<FruitStand> stands = fruitStandService.findAll();
        List<String> names = new ArrayList<>();
        for(var stand : stands) {
            var baskets = stand.getBaskets();
            for(var basket : baskets) {
                if(!names.contains(basket.getFruitName())) {
                    names.add(basket.getFruitName());
                }
            }
        }
        return names;
    }

    public Collection<FruitBasketDTO> Baskets() {
        Collection<FruitStand> stands = fruitStandService.findAll();
        Collection<FruitBasketDTO> basketDtos = new ArrayList<>();
        for(var stand : stands) {
            var baskets = stand.getBaskets();
            for(var basket : baskets) {
                basketDtos.add(new FruitBasketDTO(stand.getName(), basket.getFruitName(), basket.getPrice()));
            }
        }
        return basketDtos;
    }

    public FruitStand getFruitStandWithBestPrice(Collection<String> selectedFruitNames) {
        var stands = fruitStandService.GetFruitStandsWithList(selectedFruitNames);
        return chooser.ChooseFruitStandWithLowestTotalPrice(stands, selectedFruitNames);
    }
}
