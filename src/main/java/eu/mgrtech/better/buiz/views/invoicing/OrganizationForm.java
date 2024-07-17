package eu.mgrtech.better.buiz.views.invoicing;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class OrganizationForm extends FormLayout {

    private final TextField name = new TextField();
    private final TextField street = new TextField();
    private final TextField vatNumber = new TextField();
    private final TextField bceNumber = new TextField();
    private final TextField address = new TextField();

    public OrganizationForm() {
        addClassName("organization-form");

        name.setPlaceholder("Name");
        street.setPlaceholder("Street and number");
        vatNumber.setPlaceholder("VAT number");
        bceNumber.setPlaceholder("BCE number");
        address.setPlaceholder("Pin and city");
        add(name);
        add(street);
        add(vatNumber);
        add(bceNumber);
        add(address);

        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    }
}
