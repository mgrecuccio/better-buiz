package eu.mgrtech.better.buiz.views.clients;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.entities.ClientStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientFormTest {

    private Client client;

    @BeforeEach
    public void setup() {
        client = new Client();
        client.setName("Test-client-name");
        client.setVatNumber("BE1234567");
        client.setIntermediary("Test-client-intermediary");
        client.setContactEmailAddress("test@email.com");
    }

    @Test
    public void testThatTheFormIsPopulatedAsExpected() {
        var form = new ClientForm();
        form.setClient(client);
        assertEquals("Test-client-name", form.name.getValue());
        assertEquals("BE1234567", form.vatNumber.getValue());
        assertEquals("Test-client-intermediary", form.intermediary.getValue());
        assertEquals("test@email.com", form.contactEmailAddress.getValue());
    }

    @Test
    public void testThatTheSaveEventPersistsNewValues() {
        var form = new ClientForm();
        var client = new Client();
        form.setClient(client);
        form.name.setValue("ClientName");
        form.vatNumber.setValue("BE123456");
        form.intermediary.setValue("John Doe");
        form.contactEmailAddress.setValue("john@doe.com");

        AtomicReference<Client> savedClientRef = new AtomicReference<>(null);

        form.addSaveListener(e -> savedClientRef.set(e.getClient()));
        form.save.click();

        var savedClient = savedClientRef.get();

        assertEquals("ClientName", savedClient.getName());
        assertEquals("BE123456", savedClient.getVatNumber());
        assertEquals("john@doe.com", savedClient.getContactEmailAddress());
        assertEquals("John Doe", savedClient.getIntermediary());
        assertEquals(ClientStatus.DRAFT, savedClient.getStatus());
    }

    @Test
    public void thestThatTheSaveEventIsNotFiredIfTheValuesAreInvalid() {
        var form = new ClientForm();
        var client = new Client();
        form.setClient(client);
        form.name.setValue("ClientName");
        form.vatNumber.setValue("BE1234567");
        form.intermediary.setValue("John Doe");
        form.contactEmailAddress.setValue("invalidemail");

        AtomicReference<Client> savedClientRef = new AtomicReference<>(null);

        form.addSaveListener(e -> savedClientRef.set(e.getClient()));
        form.save.click();

        var savedClient = savedClientRef.get();

        assertNull(savedClient);
    }

    @Test
    public void testThatSaveAndCancelButtonsArePresent() {
        var form = new ClientForm();
        var save = form.save;

        assertTrue(save.isVisible());
        assertTrue(save.isEnabled());

        var close = form.close;
        assertTrue(close.isVisible());
        assertTrue(close.isEnabled());
    }
}