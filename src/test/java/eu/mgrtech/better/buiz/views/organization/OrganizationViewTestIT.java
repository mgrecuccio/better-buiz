package eu.mgrtech.better.buiz.views.organization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import eu.mgrtech.better.buiz.services.OrganizationService;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = { OrganizationView.class, OrganizationService.class })
class OrganizationViewTestIT {

    @Autowired
    private OrganizationView organizationView;

    @Test
    public void testThatTheViewIsStructuredAsExpected() {
        var viewTitle = organizationView.viewTitle;
        var infoBoxContent = organizationView.infoBoxContent;

        assertEquals("Company Details", viewTitle.getText());
        assertEquals("The information is taken directly from the BCE - Banque-Carrefour des Entreprises.", infoBoxContent.getText());

        var organizationInfoForm = organizationView.organizationInfoForm;

        assertEquals("BE1004927621", organizationInfoForm.vatNumber.getValue());
        assertEquals("MGRTECH", organizationInfoForm.companyName.getValue());
        assertEquals("19-01-2024", organizationInfoForm.startDate.getValue());
        assertEquals("Rue Servandoni, 45 - 1130 Haren", organizationInfoForm.address.getValue());
        assertEquals("Limited liability company", organizationInfoForm.companyType.getValue());
        assertEquals("ACTIVE", organizationInfoForm.companyStatus.getValue());
        assertEquals("test@gmail.com", organizationInfoForm.emailAddress.getValue());
        assertEquals("Marco Grecuccio", organizationInfoForm.generalManager.getValue());
    }

}
