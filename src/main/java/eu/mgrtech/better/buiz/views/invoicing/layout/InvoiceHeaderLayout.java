package eu.mgrtech.better.buiz.views.invoicing.layout;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import eu.mgrtech.better.buiz.views.invoicing.form.ClientForm;
import eu.mgrtech.better.buiz.views.invoicing.form.OrganizationForm;

public class InvoiceHeaderLayout extends HorizontalLayout {

    private static final String CLIENT_INFORMATION_LABEL = "Client information";
    private static final String ORGANIZATION_INFORMATION_LABEL = "Organization information";

    private VerticalLayout organizationLayout = new VerticalLayout();
    private VerticalLayout clientLayout = new VerticalLayout();

    OrganizationForm organizationForm = new OrganizationForm();
    ClientForm clientForm = new ClientForm();

    public InvoiceHeaderLayout() {
        addClassName("invoice-header");
        setWidthFull();

        configureOrganizationLayout();
        configureClientLayout();

        add(organizationLayout, clientLayout);
    }

    private void configureOrganizationLayout() {
        organizationLayout.addClassName("organization-layout");

        var organizationLabel = new Paragraph(ORGANIZATION_INFORMATION_LABEL);
        organizationLabel.addClassName("form-label");

        organizationLayout.add(organizationLabel, organizationForm);
    }

    private void configureClientLayout() {
        clientLayout.addClassName("client-form-layout");

        var clientLabel = new Paragraph(CLIENT_INFORMATION_LABEL);
        clientLabel.addClassName("form-label");

        clientLayout.add(clientLabel, clientForm);
    }
}
