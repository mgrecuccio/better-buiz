package eu.mgrtech.better.buiz.views.invoicing.layout;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

public class AmountLayout extends VerticalLayout {

    private final FormLayout amountForm = new FormLayout();
    private final NumberField amountWithVatField = new NumberField();
    private Div euroPrefix = new Div();

    public AmountLayout() {
        addClassName("amount-layout");

        amountWithVatField.addClassName("amount-with-vat-field");
        amountWithVatField.setReadOnly(true);

        euroPrefix.setText("€");
        amountWithVatField.setPrefixComponent(euroPrefix);
        amountForm.addFormItem(amountWithVatField, new H4("Total amount"));
        amountForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        add(amountForm);
    }

    public void updateAmount(double amount) {
        amountWithVatField.setValue(amount);
    }
}
