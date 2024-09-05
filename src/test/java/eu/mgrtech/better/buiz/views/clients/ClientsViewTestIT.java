package eu.mgrtech.better.buiz.views.clients;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import eu.mgrtech.better.buiz.entities.Client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientsViewTestIT {

    @Autowired
    private ClientsView clientsView;

    @Test
    public void testThatTheClientGridIsPopulated() {
        Grid<Client> clientsGrid = clientsView.clientsGrid;
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
        TextField filterClients = clientsView.filterClients;

        assertTrue(filterClients.isVisible());
        assertTrue(filterClients.isEnabled());
        assertFalse(filterClients.isReadOnly());

        assertEquals(ValueChangeMode.LAZY, filterClients.getValueChangeMode());
    }

    @Test
    public void testThatTheAddClientButtonIsVisibleAndWhenClickedTheClientFormIsOpened() {
        var clientForm = clientsView.clientForm;
        assertFalse(clientForm.isVisible());

        clientsView.addClientButton.click();
        assertTrue(clientForm.isVisible());

        clientForm.close.click();
        assertFalse(clientForm.isVisible());
    }

}