package eu.mgrtech.better.buiz.views.invoicing.form;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import eu.mgrtech.better.buiz.views.invoicing.layout.AddressLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.StreetLayout;

public class ClientForm extends FormLayout {

    private static final String NAME = "Name";

    TextField name = new TextField();
    AddressLayout addressLayout = new AddressLayout();
    StreetLayout streetLayout = new StreetLayout();

    public ClientForm() {
        addClassName("client-form");

        name.setPlaceholder(NAME);

        add(name, addressLayout, streetLayout);
        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    }
}
