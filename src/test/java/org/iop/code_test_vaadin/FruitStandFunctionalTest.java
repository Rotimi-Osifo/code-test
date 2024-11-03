package org.iop.code_test_vaadin;


import org.iop.code_test_vaadin.data.CreateTestData;
import org.iop.code_test_vaadin.entity.FruitBasket;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.factory.FruitBasketFactory;
import org.iop.code_test_vaadin.service.FruitBasketService;
import org.iop.code_test_vaadin.service.FruitStandChooser;
import org.iop.code_test_vaadin.service.FruitStandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Transactional
@SpringBootTest(classes = CodeTestVaadinApplication .class)
public class FruitStandFunctionalTest {
    private static final Logger log = LoggerFactory.getLogger(FruitStandFunctionalTest.class);
    @Autowired
    private FruitBasketService fruitBasketService;

    @Autowired
    private FruitStandService fruitStandService;

    @Autowired
    FruitStandChooser chooser;

    @Test
    public void testFruitStandWithAFruitBasket() {
        final float epsilon = 1E-16f;
        var stand = CreateTestData.createStand();

        var savedStand = fruitStandService.save(stand);

        var basket = CreateTestData.createBasket();
        var savedBasket = this.fruitBasketService.saveBasket(basket, savedStand.getId());

        var retrievedBasket = this.fruitBasketService.getBasketById(savedBasket.getId());
        Assertions.assertNotNull(retrievedBasket);
        Assertions.assertNotNull(retrievedBasket.getStand());
        Assertions.assertEquals(retrievedBasket.getFruitName(), basket.getFruitName());
        Assertions.assertTrue((retrievedBasket.getPrice() - basket.getPrice()) < epsilon);
        Assertions.assertEquals(1, retrievedBasket.getStand().getBaskets().size());
    }

    @Test
    public void testFruitStands() {
        seedDataBaseWithData();
        var stands = fruitStandService.findAll();
        Assertions.assertEquals(5, stands.size());

        for(var stand : stands) {
            Assertions.assertEquals(2, stand.getBaskets().size());
        }
    }

    @Test
    public void testQueryFruitStandsWithListOfStrings() {
        seedDataBaseWithData();

        Collection<String> queryStrings = new ArrayList<>(){{add("Cherry"); add("Peach");}};
        var stands = fruitStandService.GetFruitStandsWithList(queryStrings);
        Assertions.assertEquals(5, stands.size());
    }

    @Test
    public void testFruitStandChooser() {
        FruitStand stand = new FruitStand("Lowest total price");
        var savedStand = fruitStandService.save(stand);

        float priceForCherriesBasket = 19.10f;
        float priceForPeachesBasket = 15.10f;
        String cherryFruit = "Cherry";
        String peachFruit = "Peach";
        FruitBasket cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        var savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        FruitBasket peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);
        var savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        seedDataBaseWithData(); // add 5 more stands

        Collection<String> queryStrings = new ArrayList<>(){{add("Cherry"); add("Peach");}}; //users choice of fruit baskets
        var stands = fruitStandService.GetFruitStandsWithList(queryStrings);
        Assertions.assertEquals(6, stands.size());
        FruitStand selectedStand = chooser.ChooseFruitStandWithLowestTotalPrice(stands);
        Assertions.assertEquals(stand.getId(), selectedStand.getId());
        log.info(selectedStand.toString());
    }

    @Test
    public void testAddingMoreFruits() {
        FruitStand stand = new FruitStand("More fruits");
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

        var lowestPriceStand = new FruitStand("Lowest total price");
        savedStand = fruitStandService.save(lowestPriceStand);

        float priceForCherriesBasket = 19.10f;
        float priceForPeachesBasket = 15.10f;
        String cherryFruit = "Cherry";
        String peachFruit = "Peach";
        FruitBasket cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        var savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        FruitBasket peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);
        var savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        var stands = fruitStandService.findAll();
        Assertions.assertEquals(7, stands.size());

        Collection<String> queryStrings = new ArrayList<>(){{add("Cherry"); add("Peach");}}; //users choice of fruit baskets
        stands = fruitStandService.GetFruitStandsWithList(queryStrings);
        Assertions.assertEquals(6, stands.size());
        FruitStand selectedStand = chooser.ChooseFruitStandWithLowestTotalPrice(stands, queryStrings);
        Assertions.assertEquals(lowestPriceStand.getId(), selectedStand.getId());
        log.info(selectedStand.toString());
    }

    @Test
    public void testWithSeveralFruitsTypesInOneStand() {
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

        var stands = fruitStandService.findAll();
        Assertions.assertEquals(6, stands.size());

        Collection<String> queryStrings = new ArrayList<>(){{add("Cherry"); add("Peach");}}; //users choice of fruit baskets
        stands = fruitStandService.GetFruitStandsWithList(queryStrings);
        Assertions.assertEquals(6, stands.size());
        FruitStand selectedStand = chooser.ChooseFruitStandWithLowestTotalPrice(stands, queryStrings);
        Assertions.assertEquals(stand.getId(), selectedStand.getId());
        log.info(selectedStand.toString());
    }

    @Test
    public void testWithSeveralFruitsTypesInOneStandSingleSelection() {
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

        var stands = fruitStandService.findAll();
        Assertions.assertEquals(6, stands.size());

        Collection<String> queryStrings = new ArrayList<>(){{add("Peach");}}; //users choice of fruit baskets: single selection
        stands = fruitStandService.GetFruitStandsWithList(queryStrings);
        Assertions.assertEquals(6, stands.size());
        FruitStand selectedStand = chooser.ChooseFruitStandWithLowestTotalPrice(stands, queryStrings);
        Assertions.assertEquals(stand.getId(), selectedStand.getId());
        log.info(selectedStand.toString());
    }

    private void seedDataBaseWithData() {
        FruitStand stand = new FruitStand("First");
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

        stand = new FruitStand("Second");
        savedStand = fruitStandService.save(stand);
        savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        priceForCherriesBasket = 20.10f;
        priceForPeachesBasket = 15.15f;
        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        stand = new FruitStand("Third");
        savedStand = fruitStandService.save(stand);
        savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        priceForCherriesBasket = 22.35f;
        priceForPeachesBasket = 15.99f;
        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        stand = new FruitStand("Fourth");
        savedStand = fruitStandService.save(stand);
        savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());

        priceForCherriesBasket = 22.11f;
        priceForPeachesBasket = 17.20f;
        cherries = FruitBasketFactory.createFruitBasket(cherryFruit,  priceForCherriesBasket);
        peaches = FruitBasketFactory.createFruitBasket(peachFruit, priceForPeachesBasket);

        stand = new FruitStand("Fifth");
        savedStand = fruitStandService.save(stand);
        savedCherriesBasket = this.fruitBasketService.saveBasket(cherries, savedStand.getId());
        savedPeachesBasket = this.fruitBasketService.saveBasket(peaches, savedStand.getId());
    }
}
