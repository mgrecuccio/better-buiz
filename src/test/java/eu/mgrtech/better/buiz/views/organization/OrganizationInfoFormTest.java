package eu.mgrtech.better.buiz.views.organization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.mgrtech.better.buiz.entities.Organization;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganizationInfoFormTest {

    private final String vatNumber = "BE1004927621";
    private final String companyName = "MyCompany";
    private final String startDate = "19-01-2024";
    private final String address = "Rue de la Rue, 45 - 1000 Bruxelles";
    private final String companyType = "Limited liability company.";
    private final String companyStatus = "ACTIVE";
    private final String companyEmail = "test@gmail.com";
    private final String companyGeneralManager = "John Doe";

    private OrganizationInfoForm organizationInfoForm;

    @BeforeEach
    public void setUp() {
        var organization = new Organization(
                "id-1",
                vatNumber,
                companyName,
                startDate,
                address,
                companyType,
                companyStatus,
                companyEmail,
                companyGeneralManager
        );

        organizationInfoForm = new OrganizationInfoForm(organization);
    }

    @Test
    public void testThatTheFormIsCorrectlyFilled() {
        //Labels
        assertEquals("VAT Number", organizationInfoForm.vatNumber.getLabel());
        assertEquals("Company Name", organizationInfoForm.companyName.getLabel());
        assertEquals("Start Date", organizationInfoForm.startDate.getLabel());
        assertEquals("Address", organizationInfoForm.address.getLabel());
        assertEquals("Company Type", organizationInfoForm.companyType.getLabel());
        assertEquals("Company Status", organizationInfoForm.companyStatus.getLabel());
        assertEquals("Email Address", organizationInfoForm.emailAddress.getLabel());
        assertEquals("General Manager", organizationInfoForm.generalManager.getLabel());

        // Values
        assertEquals(vatNumber, organizationInfoForm.vatNumber.getValue());
        assertEquals(companyName, organizationInfoForm.companyName.getValue());
        assertEquals(startDate, organizationInfoForm.startDate.getValue());
        assertEquals(address, organizationInfoForm.address.getValue());
        assertEquals(companyType, organizationInfoForm.companyType.getValue());
        assertEquals(companyStatus, organizationInfoForm.companyStatus.getValue());
        assertEquals(companyEmail, organizationInfoForm.emailAddress.getValue());
        assertEquals(companyGeneralManager, organizationInfoForm.generalManager.getValue());
    }

    @Test
    public void testThatTheFormHasTheExpectedStructure() {
        assertTrue(organizationInfoForm.vatNumber.isReadOnly());
        assertTrue(organizationInfoForm.companyName.isReadOnly());
        assertTrue(organizationInfoForm.startDate.isReadOnly());
        assertTrue(organizationInfoForm.address.isReadOnly());
        assertTrue(organizationInfoForm.companyType.isReadOnly());
        assertTrue(organizationInfoForm.companyStatus.isReadOnly());
        assertTrue(organizationInfoForm.emailAddress.isReadOnly());
        assertTrue(organizationInfoForm.generalManager.isReadOnly());
    }
}