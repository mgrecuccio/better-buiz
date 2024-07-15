package eu.mgrtech.better.buiz.views.invoicing;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class InvoiceNumberField extends CustomField<String> {

    private TextField year;
    private TextField invoiceNumber;

    public InvoiceNumberField() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().add("spacing-xs");

        H4 label = new H4("Invoice Number");
        label.getStyle().set("align-self", "center");
        year = new TextField();
        year.setWidth("100px");
        H4 slash = new H4("/");
        slash.getStyle().set("align-self", "center");
        invoiceNumber = new TextField();
        invoiceNumber.setWidth("100px");
        layout.add(label, year, slash, invoiceNumber);
        add(layout);
    }

    @Override
    protected String generateModelValue() {
        return String.join("/", year.toString(), invoiceNumber.toString());
    }

    @Override
    protected void setPresentationValue(String invoiceNr) {
        if (invoiceNr == null) {
            year.setValue(null);
            invoiceNumber.setValue(null);
        } else {
            String[] expirationParts = invoiceNr.split("/");
            year.setValue(expirationParts[0]);
            invoiceNumber.setValue(expirationParts[1]);
        }
    }
}
