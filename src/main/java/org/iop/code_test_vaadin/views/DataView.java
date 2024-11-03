package org.iop.code_test_vaadin.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.Div;
import org.iop.code_test_vaadin.DTO.FruitBasketDTO;
import org.iop.code_test_vaadin.entity.FruitStand;
import org.iop.code_test_vaadin.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@PageTitle("Data View")
@Route(value = "")
public class DataView  extends VerticalLayout {

    private static final Logger log = LoggerFactory.getLogger(DataView.class);

    @Autowired
    private final DataService dataService;

    private final Grid<FruitBasketDTO> grid = new Grid<>(FruitBasketDTO.class);
    private final MultiSelectComboBox<String> comboBox = new MultiSelectComboBox<>("Select Fruits");
    private final Button loadDataButton = new Button("Load Fruits Stand Data");
    private final Button standChooserButton = new Button("Get Fruit Stand with Best Price");
    private final TextField textField  = new TextField();
    @Autowired
    public DataView(DataService dataService) {
        this.dataService = dataService;

        loadDataButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        loadDataButton.addClickListener(new LoadDataEventListener(dataService, grid, comboBox, loadDataButton));
        Div firstDiv = new Div();
        firstDiv.add(loadDataButton);

        comboBox.setWidth("300px");
        Div secondDiv = new Div();
        secondDiv.add(comboBox);


        textField.setPlaceholder("Best Price Fruit Stand");
        textField.setLabel("Best Price Stand");

        standChooserButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        standChooserButton.addClickListener(new LoadDataEventListener(dataService, textField, comboBox));
        Div butttonDiv = new Div();
        butttonDiv.add( standChooserButton);

        addComponentAsFirst(firstDiv);
        addComponentAtIndex(1, secondDiv);
        addComponentAtIndex(2, grid);
        addComponentAtIndex(3, butttonDiv);
        addComponentAtIndex(4, textField);
    }

    private static class LoadDataEventListener implements ComponentEventListener<ClickEvent<Button>> {
        private final DataService dataService;
        private final Grid<FruitBasketDTO> grid;
        private final MultiSelectComboBox<String> comboBox;
        private final Button button;
        private final TextField textField;

        public LoadDataEventListener(DataService dataService,
                                     Grid<FruitBasketDTO> grid,
                                     MultiSelectComboBox<String> comboBox,
                                     Button button) {
            this.comboBox = comboBox;
            this.dataService = dataService;
            this.grid = grid;
            this.button = button;
            this.textField = null;
        }

        public LoadDataEventListener(DataService dataService,
                                     TextField textField,
                                     MultiSelectComboBox<String> comboBox) {
            this.comboBox = comboBox;
            this.dataService = dataService;
            this.grid = null;
            this.button = null;
            this.textField = textField;
        }

        @Override
        public void onComponentEvent(ClickEvent<Button> event) {

            if(grid != null && button != null) {
                dataService.seedDataBase();

                var items = this.dataService.BasketNames();
                comboBox.setItems(items);
                comboBox.setClassNameGenerator((item) -> {
                    return switch (item) {
                        case "Apple" -> "coral";
                        case "Cherry" -> "gold";
                        case "Orange" -> "orange";
                        case "Pear" -> "yellowgreen";
                        case "Peach" -> "yellow";
                        case "Avocado" -> "green";
                        default -> "";
                    };
                });
                this.comboBox.select(items.subList(0, 2));

                this.grid.setItems(this.dataService.Baskets());

                this.button.setEnabled(false);
            } else if(dataService != null && textField != null && comboBox != null) {
                Set<String> selectedBaskets = comboBox.getSelectedItems();

                textField.setHeightFull();
                textField.setWidthFull();
                textField.setSizeFull();
                var selectedStand =  dataService.getFruitStandWithBestPrice(selectedBaskets);
                StringBuilder textForDisplay = getTextForDisplay(selectedStand, selectedBaskets);

                textField.setPlaceholder(textForDisplay.toString());
                log.info(textField.getPlaceholder());
            }

        }

        private StringBuilder getTextForDisplay(FruitStand selectedStand, Set<String> selectedBaskets) {

            StringBuilder textForDisplay = new StringBuilder("Stand name: ");
            textForDisplay.append(selectedStand.getName());
            textForDisplay.append("; ");

            float total = 0.0f;
            for(var basket : selectedStand.getBaskets()) {
                if(selectedBaskets.contains(basket.getFruitName())) {
                    textForDisplay.append("Basket name: ");
                    textForDisplay.append(basket.getFruitName());
                    textForDisplay.append("; ");
                    textForDisplay.append("Basket Price: ");
                    textForDisplay.append(basket.getPrice());
                    textForDisplay.append("; ");
                    total += basket.getPrice();
                }
            }
            textForDisplay.append("Total price for selected baskets: ");
            textForDisplay.append(total);
            textForDisplay.append("\n");
            return textForDisplay;
        }


    }
}
