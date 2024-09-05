package eu.mgrtech.better.buiz.views.clients;

import com.github.mvysny.kaributesting.v10.LocatorJ;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.views.UITestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientsViewTestUI_IT extends UITestBase {

    @Test
    public void testThatTheViewIsNotAccessibleForNonLoggedUsers() {
        UI.getCurrent().navigate(ClientsView.class);

        var error = assertThrows(AssertionError.class, () -> LocatorJ._get(Grid.class, spec -> spec.withClasses("clients-grid")));
        assertTrue(error.getMessage().contains("/login: No visible Grid in UI[] matching Grid and classes='clients-grid'"));
    }

    @Test
    public void testThatTheClientGridIsPopulated() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(ClientsView.class);

        var clientsGrid = LocatorJ._get(Grid.class, spec -> spec.withClasses("clients-grid"));
        assertTrue(clientsGrid.isVisible());

        List<Grid.Column<Client>> columns = clientsGrid.getColumns();
        assertEquals(5, columns.size());

        Grid.Column<Client> vatNumber = columns.get(0);
        assertEquals("vatNumber", vatNumber.getKey());

        Grid.Column<Client> clientName = columns.get(1);
        assertEquals("name", clientName.getKey());

        Grid.Column<Client> intermediary = columns.get(2);
        assertEquals("intermediary", intermediary.getKey());

        Grid.Column<Client> intermediaryEMail = columns.get(3);
        assertEquals("contactEmailAddress", intermediaryEMail.getKey());

        Grid.Column<Client> status = columns.get(4);
        assertEquals("Status", status.getHeaderText());

        var clients = clientsGrid.getGenericDataView().getItems().toList();
        assertFalse(clients.isEmpty());
    }

    @Test
    public void testThatTheFilterTextIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(ClientsView.class);

        var filterClients = LocatorJ._get(TextField.class, spec -> spec.withClasses("filter-clients"));

        assertTrue(filterClients.isVisible());
        assertTrue(filterClients.isEnabled());
        assertFalse(filterClients.isReadOnly());

        assertEquals(ValueChangeMode.LAZY, filterClients.getValueChangeMode());
    }

    @Test
    public void testThatClientFormIsClosedWhenCloseButtonIsClicked() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(ClientsView.class);

        assertThrows(AssertionError.class, () -> LocatorJ._get(ClientForm.class, spec -> spec.withClasses("client-form")));

        var addClientBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("add-client-btn"));
        addClientBtn.click();

        var clientForm = LocatorJ._get(ClientForm.class, spec -> spec.withClasses("client-form"));
        assertTrue(clientForm.isVisible());

        var closeClientFormBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("close-client-form-btn"));
        closeClientFormBtn.click();

        assertFalse(clientForm.isVisible());
    }

    @Test
    public void testThatTheAddClientButtonIsVisibleAndWhenClickedTheClientFormIsOpened() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(ClientsView.class);

        assertThrows(AssertionError.class, () -> LocatorJ._get(ClientForm.class, spec -> spec.withClasses("client-form")));

        var addClientBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("add-client-btn"));
        addClientBtn.click();

        var clientForm = LocatorJ._get(ClientForm.class, spec -> spec.withClasses("client-form"));
        assertTrue(clientForm.isVisible());
    }

    @Test
    public void testThatNoNewClientIsPersistedWhenSaveButtonIsPressedWithInvalidValues() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(ClientsView.class);

        var clientsGrid = LocatorJ._get(Grid.class, spec -> spec.withClasses("clients-grid"));
        var originalClients = clientsGrid.getGenericDataView().getItems().toList();

        var addClientBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("add-client-btn"));
        addClientBtn.click();

        var vatNumber = LocatorJ._get(TextField.class, spec -> spec.withClasses("vat-number"));
        var name = LocatorJ._get(TextField.class, spec -> spec.withClasses("name"));
        var intermediary = LocatorJ._get(TextField.class, spec -> spec.withClasses("intermediary"));
        var emailField = LocatorJ._get(EmailField.class, spec -> spec.withClasses("intermediary-email"));

        vatNumber.setValue("invalid-vat-number");
        name.setValue("ClientName");
        intermediary.setValue("Intermediary");
        emailField.setValue("invalid-email");

        var saveClientBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("save-client-btn"));
        saveClientBtn.click();

        var updatedClients = clientsGrid.getGenericDataView().getItems().toList();
        assertEquals(originalClients.size(), updatedClients.size());
    }

    @Test
    public void testThatTheNewClientIsPersistedWhenSaveButtonIsPressed() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(ClientsView.class);

        var clientsGrid = LocatorJ._get(Grid.class, spec -> spec.withClasses("clients-grid"));
        var originalClients = clientsGrid.getGenericDataView().getItems().toList();
        assertEquals(5, originalClients.size());

        var addClientBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("add-client-btn"));
        addClientBtn.click();

        var vatNumber = LocatorJ._get(TextField.class, spec -> spec.withClasses("vat-number"));
        var name = LocatorJ._get(TextField.class, spec -> spec.withClasses("name"));
        var intermediary = LocatorJ._get(TextField.class, spec -> spec.withClasses("intermediary"));
        var emailField = LocatorJ._get(EmailField.class, spec -> spec.withClasses("intermediary-email"));

        vatNumber.setValue("BE123456");
        name.setValue("ClientName");
        intermediary.setValue("Intermediary");
        emailField.setValue("test@email.com");

        var saveClientBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("save-client-btn"));
        saveClientBtn.click();

        var updatedClients = clientsGrid.getGenericDataView().getItems().toList();
        assertEquals(originalClients.size() + 1, updatedClients.size());
    }
}