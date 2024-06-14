package eu.mgrtech.better.buiz.views.organization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationViewTest {

    private OrganizationView organizationView;

    @BeforeEach
    public void setUp() {
        organizationView = new OrganizationView();
    }

    @Test
    public void organizationViewContentTest() {
        assertEquals("Company Details", organizationView.viewTitle.getText());
        assertEquals("The information is taken directly from the BCE - Banque-Carrefour des Entreprises.", organizationView.infoBoxContent.getText());
        assertNotNull(organizationView.organizationInfoForm);
    }
}