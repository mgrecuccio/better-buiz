package eu.mgrtech.better.buiz.views.organization;

import eu.mgrtech.better.buiz.services.OrganizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrganizationViewTest {

    private OrganizationView organizationView;

    private OrganizationService organizationService;

    @BeforeEach
    public void setUp() {
        organizationService = new OrganizationService();
        organizationView = new OrganizationView(organizationService);
    }

    @Test
    public void organizationViewContainsInfoFormAndInfoBannerTest() {
        assertEquals("Company Details", organizationView.viewTitle.getText());
        assertEquals("The information is taken directly from the BCE - Banque-Carrefour des Entreprises.", organizationView.infoBoxContent.getText());
        assertNotNull(organizationView.organizationInfoForm);
    }
}