package eu.mgrtech.better.buiz.views.clients;

import com.vaadin.flow.component.button.Button;
import eu.mgrtech.better.buiz.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

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
    public void formFieldsPopulatedTest() {
        ClientForm form = new ClientForm();
        form.setClient(client);
        assertEquals("Test-client-name", form.name.getValue());
        assertEquals("BE1234567", form.vatNumber.getValue());
        assertEquals("Test-client-intermediary", form.intermediary.getValue());
        assertEquals("test@email.com", form.contactEmailAddress.getValue());
    }

    @Test
    public void saveEventHasCorrectValuesTest() {
        ClientForm form = new ClientForm();
        Client client = new Client();
        form.setClient(client);
        form.name.setValue("ClientName");
        form.vatNumber.setValue("BE123456");
        form.intermediary.setValue("John Doe");
        form.contactEmailAddress.setValue("john@doe.com");

        AtomicReference<Client> savedClientRef = new AtomicReference<>(null);

        form.addSaveListener(e -> savedClientRef.set(e.getClient()));
        form.save.click();

        Client savedClient = savedClientRef.get();

        assertEquals("ClientName", savedClient.getName());
        assertEquals("BE123456", savedClient.getVatNumber());
        assertEquals("john@doe.com", savedClient.getContactEmailAddress());
        assertEquals("John Doe", savedClient.getIntermediary());
        assertEquals(ClientStatus.DRAFT, savedClient.getStatus());
    }

    @Test
    public void saveEventDoesNotOccurIfValuesAreInvalid() {
        ClientForm form = new ClientForm();
        Client client = new Client();
        form.setClient(client);
        form.name.setValue("ClientName");
        form.vatNumber.setValue("BE1234567");
        form.intermediary.setValue("John Doe");
        form.contactEmailAddress.setValue("invalidemail");

        AtomicReference<Client> savedClientRef = new AtomicReference<>(null);

        form.addSaveListener(e -> savedClientRef.set(e.getClient()));
        form.save.click();

        Client savedClient = savedClientRef.get();

        assertNull(savedClient);
    }

    @Test
    public void saveAndCancelButtonArePresentTest() {
        ClientForm form = new ClientForm();
        Button save = form.save;

        assertTrue(save.isVisible());
        assertTrue(save.isEnabled());

        Button close = form.close;
        assertTrue(close.isVisible());
        assertTrue(close.isEnabled());
    }
}