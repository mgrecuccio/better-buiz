package eu.mgrtech.better.buiz.views.invoicing;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ClientForm extends FormLayout {

    private final TextField name = new TextField();
    private final TextField street = new TextField();
    private final TextField address = new TextField();

    public ClientForm() {
        addClassName("client-form");

        name.setPlaceholder("Client's name");
        street.setPlaceholder("Client's street");
        address.setPlaceholder("Client's pin and city");

        add(name);
        add(street);
        add(address);
        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    }
}
