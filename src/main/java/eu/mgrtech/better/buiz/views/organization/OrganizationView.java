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
import eu.mgrtech.better.buiz.services.OrganizationService;
import eu.mgrtech.better.buiz.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@PageTitle("Organization | Better Buiz")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class OrganizationView extends Composite<VerticalLayout> {

    private static final String FORM_NAME = "Company Details";
    private static final String INFOBOX_CONTENT = "The information is taken directly from the BCE - Banque-Carrefour des Entreprises.";

    private final OrganizationService organizationService;

    OrganizationInfoForm organizationInfoForm;
    H2 viewTitle = new H2(FORM_NAME);
    HorizontalLayout infoBox = new HorizontalLayout();
    Paragraph infoBoxContent = new Paragraph(INFOBOX_CONTENT);

    public OrganizationView(OrganizationService organizationService) {
        this.organizationService = organizationService;

        getContent().addClassName("view-content");
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        configureOrganizationForm();
        configureInfoBox();
        getContent().add(viewTitle, organizationInfoForm, infoBox);
    }

    public void configureOrganizationForm() {
        organizationInfoForm = new OrganizationInfoForm(organizationService.getOrganizationInfoByVatNumber("BE1004927621"));
        organizationInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));
    }

    public void configureInfoBox() {
        infoBox.addClassName("info-box");
        infoBox.addClassName(Gap.MEDIUM);

        infoBoxContent.addClassName("info-box-content");
        infoBox.add(infoBoxContent);
    }
}
