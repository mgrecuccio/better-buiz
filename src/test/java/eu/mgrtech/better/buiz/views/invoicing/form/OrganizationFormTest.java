package eu.mgrtech.better.buiz.views.invoicing.form;

import org.junit.jupiter.api.Test;

import com.vaadin.flow.component.html.Div;

import static com.helger.commons.mock.CommonsAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganizationFormTest {

    private final OrganizationForm organizationForm = new OrganizationForm();

    @Test
    public void testThatOrganizationFormIsStructuredAsExpected() {
        var vatNumber = organizationForm.vatNumber;
        assertTrue(vatNumber.isVisible());
        assertEquals("BE",((Div)vatNumber.getPrefixComponent()).getText());

        var bceNumber = organizationForm.bceNumber;
        assertTrue(bceNumber.isVisible());
        assertEquals("BCE number",bceNumber.getPlaceholder());

        var name = organizationForm.name;
        assertTrue(name.isVisible());
        assertEquals("Name",name.getPlaceholder());

        var addressLayout = organizationForm.addressLayout;
        assertNotNull(addressLayout);
        assertTrue(addressLayout.isVisible());

        var streetLayout = organizationForm.streetLayout;
        assertNotNull(streetLayout);
        assertTrue(streetLayout.isVisible());
    }

}