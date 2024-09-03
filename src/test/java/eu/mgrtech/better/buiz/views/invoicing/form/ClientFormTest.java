package eu.mgrtech.better.buiz.views.invoicing.form;

import org.junit.jupiter.api.Test;

import static com.helger.commons.mock.CommonsAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientFormTest {

    private final ClientForm clientForm = new ClientForm();

    @Test
    public void testThatTheClientFormIsStructuredAsExpected() {
        var addressLayout = clientForm.addressLayout;
        assertNotNull(addressLayout);
        assertTrue(addressLayout.isVisible());

        var name = clientForm.name;
        assertNotNull(name);
        assertTrue(name.isVisible());
        assertEquals("Name",name.getPlaceholder());

        var streetLayout = clientForm.streetLayout;
        assertNotNull(streetLayout);
        assertTrue(streetLayout.isVisible());
    }
}