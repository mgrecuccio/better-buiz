package eu.mgrtech.better.buiz.views.invoicing.layout;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class InvoiceInfoLayout extends VerticalLayout {

    private static final String INVOICE_INFORMATION_LABEL = "Invoice information";
    private static final String INVOICE_NUMBER = "Invoice Number";
    private static final String CLIENT_VAT = "Client VAT";
    private static final String INVOICE_DATE = "Invoice date";
    private static final String CLIENT_BCE = "Client BCE";
    private static final String DUE_DATE = "Due date";

    private final FormLayout invoiceInfoForm = new FormLayout();
    private final Paragraph invoiceInfoLabel = new Paragraph();
    private final InvoiceNumberLayout invoiceNumber = new InvoiceNumberLayout();
    private final DatePicker invoiceDate = new DatePicker();
    private final DatePicker dueDate = new DatePicker();
    private final TextField clientVat = new TextField();
    private final TextField clientBce = new TextField();

    public InvoiceInfoLayout() {
        addClassName("invoice-info-layout");

        configureHeader();
        configureForm();

        add(invoiceInfoLabel, invoiceInfoForm);
    }

    private void configureHeader() {
        invoiceInfoLabel.setText(INVOICE_INFORMATION_LABEL);
        invoiceInfoLabel.addClassName("form-label");
    }

    private void configureForm() {
        clientVat.setPrefixComponent(new Span("BE"));

        invoiceInfoForm.addFormItem(invoiceNumber, INVOICE_NUMBER);
        invoiceInfoForm.addFormItem(clientVat, CLIENT_VAT);
        invoiceInfoForm.addFormItem(invoiceDate, INVOICE_DATE);
        invoiceInfoForm.addFormItem(clientBce, CLIENT_BCE);
        invoiceInfoForm.addFormItem(dueDate, DUE_DATE);

        invoiceInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("400px", 2)
        );
    }
}
