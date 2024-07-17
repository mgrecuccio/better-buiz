package eu.mgrtech.better.buiz.views.invoicing;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import eu.mgrtech.better.buiz.views.MainLayout;

@PageTitle("Invoicing Tool")
@Route(value = "invoicing-tool", layout = MainLayout.class)
public class InvoicingToolView extends Composite<VerticalLayout> {

    private final OrganizationForm organizationForm;
    private final ClientForm clientForm;
    private final InvoiceInfoForm invoiceInfoForm;
    private final InvoiceEntryView invoiceEntryView;

    public InvoicingToolView() {
        addClassName("invoicing-tool-view");

        organizationForm = new OrganizationForm();
        clientForm = new ClientForm();
        invoiceInfoForm = new InvoiceInfoForm();
        invoiceEntryView = new InvoiceEntryView();

        getContent().add(configureInvoiceHeader(), configureInvoiceInformation(), invoiceEntryView);
        getContent().setWidth("90%");
    }

    private HorizontalLayout configureInvoiceHeader() {
        var headerLayout = new HorizontalLayout();

        var organizationFormLayout = new VerticalLayout();
        organizationFormLayout.addClassName("organization-form-layout");

        var organizationLabel = new Paragraph("Organization information");
        organizationLabel.addClassName("form-label");
        organizationFormLayout.add(organizationLabel, organizationForm);

        var clientFormLayout = new VerticalLayout();
        clientFormLayout.addClassName("client-form-layout");
        var clientLabel = new Paragraph("Client information");
        clientLabel.addClassName("form-label");
        clientFormLayout.add(clientLabel, clientForm);

        headerLayout.add(organizationFormLayout, clientFormLayout);
        return headerLayout;
    }

    private VerticalLayout configureInvoiceInformation() {
        var invoiceInfoLayout = new VerticalLayout();
        invoiceInfoLayout.addClassName("invoice-info-layout");

        var invoiceInfoLabel = new Paragraph("Invoice information");
        invoiceInfoLabel.addClassName("form-label");

        invoiceInfoLayout.add(invoiceInfoLabel, invoiceInfoForm);
        return invoiceInfoLayout;
    }

    //
    //    private VerticalLayout summaryLayout() {
    //        NumberField totalWithoutVat = new NumberField();
    //        totalWithoutVat.setReadOnly(true);
    //        totalWithoutVat.setSuffixComponent(new Span("€"));
    //        NumberField totalVat = new NumberField();
    //        totalVat.setReadOnly(true);
    //        totalVat.setSuffixComponent(new Span("€"));
    //        NumberField total = new NumberField();
    //        total.setReadOnly(true);
    //        total.setSuffixComponent(new Span("€"));
    //
    //        VerticalLayout v = new VerticalLayout();
    //        v.getThemeList().add("spacing-xs");
    //        FormLayout summaryForm = new FormLayout();
    //        summaryForm.addClassName("summary-form");
    //        summaryForm.addFormItem(totalWithoutVat, "Total VAT excluded");
    //        summaryForm.addFormItem(totalVat, "Total VAT");
    //        summaryForm.addFormItem(total, "Total");
    //        summaryForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    //        summaryForm.setWidth("150px");
    //        v.add(summaryForm);
    //        v.getStyle().set("flex-wrap", "wrap");
    //        v.getStyle().set("align-content", "end");
    //        v.setWidth("85%");
    //        return v;
    //    }
    //
    //    private HorizontalLayout bankInfo() {
    //        TextField iban = new TextField();
    //        TextField bic = new TextField();
    //        TextField communication = new TextField();
    //        HorizontalLayout h = new HorizontalLayout();
    //        h.setWidthFull();
    //        FormLayout bankInfo = new FormLayout();
    //        bankInfo.addClassName("bank-form");
    //        bankInfo.addFormItem(iban, "IBAN");
    //        bankInfo.addFormItem(bic, "BIC");
    //        bankInfo.addFormItem(communication, "Communication");
    //        bankInfo.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
    //        h.add(bankInfo);
    //        h.getStyle().set("flex-direction", "column");
    //        h.getStyle().set("align-content", "center");
    //        h.getStyle().set("flex-wrap", "wrap");
    //        return h;
    //    }
    //
    //    private VerticalLayout reminder() {
    //        VerticalLayout v = new VerticalLayout();
    //
    //        Paragraph p = new Paragraph();
    //        p.setText("Please pay the amount of €11,979.00 to the account mentioned above by 28/07/2024 mentioning\n"
    //                  + "        the reference +++999/5260/62493+++.");
    //
    //        Paragraph p2 = new Paragraph();
    //        p2.setText("Thank you for your confidence!");
    //
    //        v.add(p, p2);
    //
    //        v.getStyle().set("margin-left", "25px");
    //        v.getStyle().set("margin-top", "30px");
    //
    //        return v;
    //    }
}
