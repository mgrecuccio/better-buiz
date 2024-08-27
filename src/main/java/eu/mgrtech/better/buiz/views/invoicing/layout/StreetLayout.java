package eu.mgrtech.better.buiz.views.invoicing.layout;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public class StreetLayout extends HorizontalLayout {

    private static final String NUMBER = "Number";
    private static final String STREET = "Street";

    private final NumberField streetNr = new NumberField();
    private final TextField street = new TextField();

    public StreetLayout() {
        streetNr.getStyle().set("width", "6em");
        streetNr.setPlaceholder(NUMBER);

        street.setPlaceholder(STREET);
        street.getStyle().set("width", "11em");
        add(streetNr, street);
    }
}
