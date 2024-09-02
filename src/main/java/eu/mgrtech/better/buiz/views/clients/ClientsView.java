package eu.mgrtech.better.buiz.views.clients;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.entities.ClientStatus;
import eu.mgrtech.better.buiz.events.client.SaveOrUpdateClientEvent;
import eu.mgrtech.better.buiz.services.ClientService;
import eu.mgrtech.better.buiz.views.MainLayout;
import eu.mgrtech.better.buiz.views.clients.details.ClientDetailsView;
import jakarta.annotation.security.PermitAll;

@PageTitle("Clients | Better Buiz")
@Route(value = "clients", layout = MainLayout.class)
@PermitAll
public class ClientsView extends Composite<VerticalLayout> {

    private static final String VAT_NUMBER = "vatNumber";
    private static final String NAME = "name";
    private static final String STATUS = "Status";
    private static final String INTERMEDIARY = "intermediary";
    private static final String CONTACT_EMAIL_ADDRESS = "contactEmailAddress";
    private static final String ADD_CLIENT_BTN_LABEL = "Add Client";

    private final ClientService clientService;

    Grid<Client> clientsGrid = new Grid<>(Client.class);
    TextField filterClients = new TextField();
    ClientForm clientForm = new ClientForm();
    Button addClientButton = new Button(ADD_CLIENT_BTN_LABEL);

    public ClientsView(ClientService clientService) {
        addClassName("clients-list-view");

        this.clientService = clientService;
        getContent().setSizeFull();

        configureClientsGrid();
        configureForm();

        getContent().add(getToolbar(), getContentView());
        updateList();

        closeAddClientForm();
    }

    private void closeAddClientForm() {
        clientForm.setClient(null);
        clientForm.setVisible(false);
        removeClassName("adding");
    }

    private HorizontalLayout getContentView() {
        HorizontalLayout contentView = new HorizontalLayout();
        contentView.add(clientsGrid, clientForm);
        contentView.setFlexGrow(2, clientsGrid);
        contentView.setFlexGrow(1, clientForm);
        contentView.addClassNames("client-content-view");
        contentView.setSizeFull();

        return contentView;
    }

    public void configureClientsGrid() {
        clientsGrid.addClassNames("clients-grid");
        clientsGrid.setWidthFull();
        clientsGrid.setAllRowsVisible(true);
        clientsGrid.setColumns(VAT_NUMBER, NAME, INTERMEDIARY, CONTACT_EMAIL_ADDRESS);
        clientsGrid.addComponentColumn(clientInfo -> createStatusIcon(
                clientInfo.getStatus())).setHeader(STATUS);
        clientsGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        clientsGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        clientsGrid.addItemClickListener(e -> goToClientDetails(e.getItem()));
    }

    private void goToClientDetails(Client client) {
        UI.getCurrent().navigate(ClientDetailsView.class, client.getId().toString());
    }

    private Span createStatusIcon(ClientStatus status) {
        Span statusSpan = new Span(status.name());
        String badgeType = getBadgeTypeByStatus(status);
        statusSpan.getElement().getThemeList().add(badgeType);
        return statusSpan;
    }

    private HorizontalLayout getToolbar() {
        filterClients.setPlaceholder("Filter...");
        filterClients.setClearButtonVisible(true);
        filterClients.setValueChangeMode(ValueChangeMode.LAZY);
        filterClients.addValueChangeListener(e -> updateList());

        addClientButton.addClickListener(click -> addClient(new Client()));

        var toolbar = new HorizontalLayout(filterClients, addClientButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addClient(Client client) {
        clientForm.setClient(client);
        clientForm.setVisible(true);
        addClassName("adding");
    }

    private void updateList() {
        clientsGrid.setItems(clientService.getClientInfoByCompanyVatNumber(filterClients.getValue()));
    }

    private void configureForm() {
        clientForm = new ClientForm();
        clientForm.setWidth("25em");

        clientForm.addSaveListener(this::saveClient);
        clientForm.addCloseListener(e -> closeAddClientForm());
    }

    private void saveClient(SaveOrUpdateClientEvent event) {
        clientService.saveClient(event.getClient());
        updateList();
        closeAddClientForm();
    }

    private static String getBadgeTypeByStatus(ClientStatus status) {
        return switch (status) {
            case ACTIVE -> "badge success";
            case INACTIVE -> "badge contrast";
            case DECLINED -> "badge error";
            default -> "badge";
        };
    }
}
