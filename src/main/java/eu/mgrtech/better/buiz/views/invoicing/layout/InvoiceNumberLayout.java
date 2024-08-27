package eu.mgrtech.better.buiz.views.invoicing.layout;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;

public class InvoiceNumberLayout extends HorizontalLayout {

    private static final String INVOICING_NR_TOOLTIP = "The invoice identifier.";
    private static final String INVOICING_YEAR_TOOLTIP = "Usually represents the current year.";

    private NumberField invoicingYear = new NumberField();
    private NumberField invoiceNumber = new NumberField();

    public InvoiceNumberLayout() {
        invoicingYear.getStyle().set("width", "6em");
        invoicingYear.setTooltipText(INVOICING_YEAR_TOOLTIP);

        invoiceNumber.getStyle().set("width", "5em");
        invoiceNumber.setTooltipText(INVOICING_NR_TOOLTIP);
        add(invoicingYear, invoiceNumber);
    }
}
