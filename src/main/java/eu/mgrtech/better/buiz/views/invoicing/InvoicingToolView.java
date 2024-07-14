package eu.mgrtech.better.buiz.views.invoicing;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
    TextField organizationStreetNumber = new TextField();
    TextField organizationCity = new TextField();
    TextField organizationPostalCode = new TextField();
    TextField organizationVatNumber = new TextField();
    TextField organizationBCENumber = new TextField();

    // client fields
    TextField clientName = new TextField();
    TextField clientAddress = new TextField();
    TextField clientCity = new TextField();

    public InvoicingToolView() {
        addClassName("invoicing-tool-view");
        getContent().setSizeFull();
        getContent().getStyle().set("margin-left", "10%");
        getContent().add(buildHeader(), buildInvoiceInfo());
    }

    private HorizontalLayout buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.getStyle().set("width", "80%");

        VerticalLayout organizationHeader = new VerticalLayout();
        organizationHeader.addClassName("organization-header");
        organizationName.setPlaceholder("Organization name");
        organizationAddress.setPlaceholder("Address");
        organizationStreetNumber.setPlaceholder("Nr.");
        organizationCity.setPlaceholder("City");
        organizationPostalCode.setPlaceholder("Code");
        Span postalCodeAndCity = new Span();
        organizationPostalCode.setWidth("18%");
        organizationCity.setWidth("50%");
        organizationCity.getStyle().set("margin-left", "4%");
        postalCodeAndCity.add(organizationPostalCode, organizationCity);
        organizationStreetNumber.getStyle().set("width", "50px");

        HorizontalLayout vat = new HorizontalLayout();
        Paragraph vat1 = new Paragraph("VAT");
        vat1.setWidth("8%");
        vat.add(vat1, organizationVatNumber);

        HorizontalLayout bce = new HorizontalLayout();
        Paragraph bce1 = new Paragraph("BCE");
        bce1.setWidth("8%");
        bce.add(bce1, organizationBCENumber);

        organizationHeader.add(organizationName, organizationAddress, organizationStreetNumber, postalCodeAndCity, vat, bce);

        VerticalLayout clientHeader = new VerticalLayout();
        clientHeader.addClassName("client-header");
        clientName.setPlaceholder("Client name");
        clientAddress.setPlaceholder("Address");
        clientCity.setPlaceholder("City");

        clientHeader.add(clientName, clientAddress, clientCity);
        clientHeader.setAlignItems(FlexComponent.Alignment.END);
        clientHeader.getStyle().set("margin-top", "6%");

        header.add(organizationHeader, clientHeader);
        return header;
    }

    private VerticalLayout buildInvoiceInfo() {
        VerticalLayout invoiceInfo = new VerticalLayout();
        invoiceInfo.addClassName("invoice-info");
        invoiceInfo.getStyle().set("width", "80%");

        HorizontalLayout invoiceNr = new HorizontalLayout();
        H4 invoiceNr1 = new H4("INVOICE Nr.");
        invoiceNr1.getStyle().set("align-self", "center");
        TextField invoiceNrText = new TextField();
        invoiceNr.add(invoiceNr1, invoiceNrText);

        FormLayout dates = new FormLayout();
        dates.getStyle().set("margin-left", "1%");
        dates.addFormItem(new DatePicker(), "Date");
        dates.addFormItem(new DatePicker(), "Due Date");
        dates.addFormItem(new TextField(), "VAT Client");
        dates.addFormItem(new TextField(), "BCE Client");
        dates.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));

        invoiceInfo.add(invoiceNr, dates);
        return invoiceInfo;
    }

}
