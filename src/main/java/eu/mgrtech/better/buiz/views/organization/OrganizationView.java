package eu.mgrtech.better.buiz.views.organization;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import eu.mgrtech.better.buiz.views.MainLayout;

@PageTitle("Organization")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class OrganizationView extends Composite<VerticalLayout> {

    public OrganizationView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();

        H2 pageTitle = new H2("Company Details");
        layoutColumn2.add(pageTitle);

        TextField vatNumber = new TextField("VAT Number");
        vatNumber.setReadOnly(true);
        vatNumber.setValue("BE1004927621");

        TextField companyName = new TextField("Company Name");
        companyName.setReadOnly(true);
        companyName.setValue("MGRTECH");

        TextField startDate = new TextField("Start Date");
        startDate.setReadOnly(true);
        startDate.setValue("19-01-2024");

        TextField address = new TextField("Address");
        address.setReadOnly(true);
        address.setValue("Rue Servandoni, 45 - 1130 Haren");

        TextField entityType = new TextField("Entity Type");
        entityType.setReadOnly(true);
        entityType.setValue("Limited liability company.");

        TextField companyStatus = new TextField("Company Status");
        companyStatus.setReadOnly(true);
        companyStatus.setValue("ACTIVE");

        TextField emailAddress = new TextField("Email Address");
        emailAddress.setReadOnly(true);
        emailAddress.setValue("test@gmail.com");

        TextField generalManager = new TextField("General Manager");
        generalManager.setReadOnly(true);
        generalManager.setValue("Marco Grecuccio");

        FormLayout formLayout = new FormLayout();
        formLayout.add(
                vatNumber,
                companyName,
                startDate,
                companyStatus,
                address,
                entityType,
                emailAddress,
                generalManager
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));
// Stretch the username field over 2 columns
//        formLayout.setColspan(vatNumber, 2);

        layoutColumn2.add(formLayout);

        HorizontalLayout layoutRow = new HorizontalLayout();
        Paragraph textSmall = new Paragraph();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        textSmall.setText(
                "The data are taken directly from the BCE - Banque-Carrefour des Entreprises.");
        textSmall.setWidth("100%");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        getContent().add(layoutColumn2);
        getContent().add(layoutRow);
        layoutRow.add(textSmall);
    }
}
