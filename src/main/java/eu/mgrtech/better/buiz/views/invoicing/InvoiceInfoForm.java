package eu.mgrtech.better.buiz.views.invoicing;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class InvoiceInfoForm extends FormLayout {

    private final TextField invoiceNumber = new TextField();
    private final DatePicker date = new DatePicker();
    private final TextField clientVat = new TextField();
    private final TextField clientBce = new TextField();

    public InvoiceInfoForm() {
        addClassName("invoice-info-form");

        addFormItem(invoiceNumber, "Invoice Number");
        addFormItem(clientVat, "Client VAT");
        addFormItem(date, "Date");
        addFormItem(clientBce, "Client BCE");

        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new ResponsiveStep("400px", 2));
    }
}
