package eu.mgrtech.better.buiz.views.clients;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.BackEndDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import eu.mgrtech.better.buiz.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientsViewTest {

    private ClientService clientService;
    private ClientsView clientsView;

    @BeforeEach
    public void setUp() {
        clientService = new ClientService();
        clientsView = new ClientsView(clientService);
    }

    @Test
    public void theClientGridIsPopulatedTest() {
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

        // rows correspond to the number of clients given by clientService
        int clients = clientsGrid.getGenericDataView().getItems().toList().size();
        assertEquals(clientService.getClientInfoByCompanyVatNumber("").size(), clients);
    }

    @Test
    public void filterTextIsVisibleTest() {
        TextField filterClients = clientsView.filterClients;

        assertTrue(filterClients.isVisible());
        assertTrue(filterClients.isEnabled());
        assertFalse(filterClients.isReadOnly());

        assertEquals(ValueChangeMode.LAZY, filterClients.getValueChangeMode());
    }

    @Test
    public void addClientButtonIsVisibleAndWhenClickedTheClientFormIsOpenedTest() {
        ClientForm clientForm = clientsView.clientForm;
        assertFalse(clientForm.isVisible());

        clientsView.addClientButton.click();
        assertTrue(clientForm.isVisible());

        clientForm.close.click();
        assertFalse(clientForm.isVisible());
    }

}