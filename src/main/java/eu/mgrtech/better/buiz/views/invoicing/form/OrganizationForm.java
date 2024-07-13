package eu.mgrtech.better.buiz.views.invoicing.form;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import eu.mgrtech.better.buiz.views.invoicing.layout.AddressLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.StreetLayout;

public class OrganizationForm extends FormLayout {

    private static final String NAME = "Name";
    private static final String VAT_NUMBER = "VAT number";
    private static final String VAT_PREFIX = "BE";
    private static final String BCE_NUMBER = "BCE number";

    private final TextField name = new TextField();
    private final TextField vatNumber = new TextField();
    private final TextField bceNumber = new TextField();
    private final AddressLayout addressLayout = new AddressLayout();
    private final StreetLayout streetLayout = new StreetLayout();

    public OrganizationForm() {
        addClassName("organization-form");

        name.setPlaceholder(NAME);
        vatNumber.setPlaceholder(VAT_NUMBER);
        vatNumber.setPrefixComponent(new Div(VAT_PREFIX));
        bceNumber.setPlaceholder(BCE_NUMBER);

        add(name, vatNumber, bceNumber, addressLayout, streetLayout);
        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    }
}
