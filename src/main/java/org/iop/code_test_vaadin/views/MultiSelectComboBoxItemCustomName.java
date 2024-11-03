package org.iop.code_test_vaadin.views;

import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.Div;

import java.util.List;

public class MultiSelectComboBoxItemCustomName extends Div {

    public MultiSelectComboBoxItemCustomName(String label, List<String> items) {
        MultiSelectComboBox<String> comboBox = new MultiSelectComboBox<>(label, items);
        comboBox.setClassNameGenerator((item) -> {
            return switch (item) {
                case "Apple":
                    yield "coral";
                case "Banana":
                    yield "gold";
                case "Orange":
                    yield "orange";
                case "Pear":
                    yield "yellowgreen";
                default:
                    yield "";
            };
        });
        comboBox.select(items.subList(0, 2));
        comboBox.setWidth("300px");
        add(comboBox);
    }
}
