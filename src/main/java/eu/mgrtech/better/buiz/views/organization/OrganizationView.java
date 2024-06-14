package eu.mgrtech.better.buiz.views.organization;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import eu.mgrtech.better.buiz.views.MainLayout;

@PageTitle("Organization")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class OrganizationView extends Composite<VerticalLayout> {

    private static final String FORM_NAME = "Company Details";
    private static final String INFOBOX_CONTENT = "The information is taken directly from the BCE - Banque-Carrefour des Entreprises.";

    OrganizationInfoForm organizationInfoForm;
    H2 viewTitle = new H2(FORM_NAME);
    Paragraph infoBoxContent = new Paragraph(INFOBOX_CONTENT);

    public OrganizationView() {
        var viewContent = new VerticalLayout();
        viewContent.addClassName("view-content");

        viewContent.add(viewTitle);

        organizationInfoForm = new OrganizationInfoForm(getCompanyInfo());
        organizationInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        viewContent.add(organizationInfoForm);

        var infoBox = new HorizontalLayout();
        infoBox.addClassName("info-box");
        infoBox.addClassName(Gap.MEDIUM);

        infoBoxContent.addClassName("info-box-content");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        getContent().add(viewContent);
        getContent().add(infoBox);
        infoBox.add(infoBoxContent);
    }

    private CompanyInfo getCompanyInfo() {
        return new CompanyInfo(
                "BE1004927621",
                "MGRTECH",
                "19-01-2024",
                "Rue Servandoni, 45 - 1130 Haren",
                "Limited liability company.",
                "ACTIVE",
                "test@gmail.com",
                "Marco Grecuccio"
        );
    }
}
