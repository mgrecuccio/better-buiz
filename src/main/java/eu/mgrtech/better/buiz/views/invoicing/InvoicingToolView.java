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
        getContent().add(buildHeader(), buildInvoiceNumberSection(), grid());
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
        v.setWidth("20%");

        orgForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
        orgForm.setColspan(organizationName, 3);
        orgForm.setColspan(organizationAddress, 3);
        orgForm.setColspan(organizationVatNumber, 3);
        orgForm.setColspan(organizationBCENumber, 3);
        orgForm.setColspan(organizationCity, 2);
        orgForm.setColspan(organizationPostalCode, 1);
        v.add(orgForm);


        VerticalLayout v1 = new VerticalLayout();
        v1.setWidth("20%");
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

    private HorizontalLayout buildInvoiceNumberSection() {
        HorizontalLayout h = new HorizontalLayout();
        h.getStyle().set("margin-left", "15px");
        h.getStyle().set("margin-top", "25px");
        FormLayout formLayout = new FormLayout();
        formLayout.add(new InvoiceNumberField());
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 4));
        h.add(formLayout);
        return h;
    }

    public VerticalLayout grid() {
        InvoiceEntryView entry = new InvoiceEntryView();
        return entry;
    }

}
