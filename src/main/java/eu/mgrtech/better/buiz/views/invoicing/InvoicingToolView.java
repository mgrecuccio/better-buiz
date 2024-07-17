package eu.mgrtech.better.buiz.views.invoicing;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import eu.mgrtech.better.buiz.views.MainLayout;

@PageTitle("Invoicing Tool")
@Route(value = "invoicing-tool", layout = MainLayout.class)
public class InvoicingToolView extends Composite<VerticalLayout> {

    // organization fields
    TextField organizationName = new TextField();
    TextField organizationAddress = new TextField();
    TextField organizationVatNumber = new TextField();
    TextField organizationBCENumber = new TextField();
    TextField organizationCity = new TextField();
    TextField organizationPostalCode = new TextField();

    // client fields
    TextField clientName = new TextField();
    TextField clientAddress = new TextField();
    TextField clientCity = new TextField();

    public InvoicingToolView() {
        addClassName("invoicing-tool-view");
        getContent().add(buildHeader(), buildInvoiceNumberSection(), grid(), summaryLayout(), bankInfo(), reminder());
    }

    private HorizontalLayout buildHeader() {
        VerticalLayout v = new VerticalLayout();
        FormLayout orgForm = new FormLayout();
        orgForm.addClassName("org-form");
        orgForm.add(organizationName);
        orgForm.add(organizationAddress);
        orgForm.add(organizationPostalCode);
        orgForm.add(organizationCity);
        orgForm.add(organizationVatNumber);
        orgForm.add(organizationBCENumber);
        v.setWidth("350px");

        orgForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
        orgForm.setColspan(organizationName, 3);
        orgForm.setColspan(organizationAddress, 3);
        orgForm.setColspan(organizationVatNumber, 3);
        orgForm.setColspan(organizationBCENumber, 3);
        orgForm.setColspan(organizationCity, 2);
        orgForm.setColspan(organizationPostalCode, 1);
        v.add(orgForm);


        VerticalLayout v1 = new VerticalLayout();
        v1.setWidth("350px");
        v1.getStyle().set("margin-top", "20px");
        FormLayout cliForm = new FormLayout();
        cliForm.addClassName("cli-form");
        cliForm.add(clientName);
        cliForm.add(clientCity);
        cliForm.add(clientAddress);

        cliForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        cliForm.setColspan(clientName, 2);
        cliForm.setColspan(clientCity, 2);
        cliForm.setColspan(clientAddress, 2);
        v1.add(cliForm);
        v1.getStyle().set("margin-left", "50%");
        HorizontalLayout h = new HorizontalLayout();
        h.add(v, v1);
        return h;
    }

    private VerticalLayout buildInvoiceNumberSection() {
        TextField invoiceNumber = new TextField();
        DatePicker date = new DatePicker();
        DatePicker dueDate = new DatePicker();
        TextField clientVat = new TextField();
        TextField clientBce = new TextField();

        VerticalLayout v = new VerticalLayout();
        FormLayout invoiceForm = new FormLayout();
        invoiceForm.addClassName("invoice-form");
        invoiceForm.addFormItem(invoiceNumber, "Invoice Number");
        invoiceForm.addFormItem(date, "Date");
        invoiceForm.addFormItem(dueDate, "Due Date");
        invoiceForm.addFormItem(clientVat, "Client VAT");
        invoiceForm.addFormItem(clientBce, "Client BCE");
        v.setWidth("200px");

        invoiceForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        v.add(invoiceForm);
        return v;
    }

    public VerticalLayout grid() {
        InvoiceEntryView entry = new InvoiceEntryView();
        entry.setWidthFull();
        return entry;
    }

    private VerticalLayout summaryLayout() {
        NumberField totalWithoutVat = new NumberField();
        totalWithoutVat.setReadOnly(true);
        totalWithoutVat.setSuffixComponent(new Span("€"));
        NumberField totalVat = new NumberField();
        totalVat.setReadOnly(true);
        totalVat.setSuffixComponent(new Span("€"));
        NumberField total = new NumberField();
        total.setReadOnly(true);
        total.setSuffixComponent(new Span("€"));

        VerticalLayout v = new VerticalLayout();
        v.getThemeList().add("spacing-xs");
        FormLayout summaryForm = new FormLayout();
        summaryForm.addClassName("summary-form");
        summaryForm.addFormItem(totalWithoutVat, "Total VAT excluded");
        summaryForm.addFormItem(totalVat, "Total VAT");
        summaryForm.addFormItem(total, "Total");
        summaryForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        summaryForm.setWidth("150px");
        v.add(summaryForm);
        v.getStyle().set("flex-wrap", "wrap");
        v.getStyle().set("align-content", "end");
        v.setWidth("85%");
        return v;
    }

    private HorizontalLayout bankInfo() {
        TextField iban = new TextField();
        TextField bic = new TextField();
        TextField communication = new TextField();
        HorizontalLayout h = new HorizontalLayout();
        h.setWidthFull();
        FormLayout bankInfo = new FormLayout();
        bankInfo.addClassName("bank-form");
        bankInfo.addFormItem(iban, "IBAN");
        bankInfo.addFormItem(bic, "BIC");
        bankInfo.addFormItem(communication, "Communication");
        bankInfo.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
        h.add(bankInfo);
        h.getStyle().set("flex-direction", "column");
        h.getStyle().set("align-content", "center");
        h.getStyle().set("flex-wrap", "wrap");
        return h;
    }

    private VerticalLayout reminder() {
        VerticalLayout v = new VerticalLayout();

        Paragraph p = new Paragraph();
        p.setText("Please pay the amount of €11,979.00 to the account mentioned above by 28/07/2024 mentioning\n"
                  + "        the reference +++999/5260/62493+++.");

        Paragraph p2 = new Paragraph();
        p2.setText("Thank you for your confidence!");

        v.add(p, p2);

        v.getStyle().set("margin-left", "25px");
        v.getStyle().set("margin-top", "30px");

        return v;
    }
}
