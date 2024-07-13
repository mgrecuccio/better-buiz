package eu.mgrtech.better.buiz.views.invoicing.layout;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class AddressLayout extends HorizontalLayout {

    private static final String CITY = "City";
    private static final String ZIP_CODE = "Zip code";

    private final TextField zipCode = new TextField();
    private final TextField city = new TextField();

    public AddressLayout() {
        zipCode.setMinLength(4);
        zipCode.getStyle().set("width", "6em");
        zipCode.setPlaceholder(ZIP_CODE);

        city.getStyle().set("width", "11em");
        city.setPlaceholder(CITY);
        add(zipCode, city);
    }
}
