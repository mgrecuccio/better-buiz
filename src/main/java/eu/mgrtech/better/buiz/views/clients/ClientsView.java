package eu.mgrtech.better.buiz.views.clients;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import eu.mgrtech.better.buiz.services.ClientService;
import eu.mgrtech.better.buiz.views.MainLayout;

@PageTitle("Clients")
@Route(value = "clients", layout = MainLayout.class)
public class ClientsView extends Composite<VerticalLayout> {

    private static final String VAT_NUMBER = "vatNumber";
    private static final String NAME = "name";
    private static final String STATUS = "Status";
    private static final String INTERMEDIARY = "intermediary";
    private static final String CONTACT_EMAIL_ADDRESS = "contactEmailAddress";

    private final ClientService clientService;

    Grid<ClientInfo> clientInfoGrid = new Grid<>(ClientInfo.class);
    TextField filterClients = new TextField();

    public ClientsView(ClientService clientService) {
        this.clientService = clientService;
        getContent().setSizeFull();

        configureClientsGrid();

        getContent().add(getFilterToolbar(), clientInfoGrid);
        updateList();
    }

    public void configureClientsGrid() {
        clientInfoGrid.addClassNames("clients-contact-grid");
        clientInfoGrid.setSizeFull();
        clientInfoGrid.setColumns(VAT_NUMBER, NAME, INTERMEDIARY, CONTACT_EMAIL_ADDRESS);
        clientInfoGrid.addComponentColumn(clientInfo -> createStatusIcon(
                clientInfo.getStatus())).setHeader(STATUS);
        clientInfoGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Span createStatusIcon(ClientStatus status) {
        Span statusSpan = new Span(status.name());
        String badgeType = switch (status) {
            case ACTIVE:
                yield "badge success";
            case INACTIVE:
                yield "badge contrast";
            case REFUSED:
                yield "badge error";
            default:
                yield "badge";
        };
        statusSpan.getElement().getThemeList().add(badgeType);
        return statusSpan;
    }

    private HorizontalLayout getFilterToolbar() {
        filterClients.setPlaceholder("Filter...");
        filterClients.setClearButtonVisible(true);
        filterClients.setValueChangeMode(ValueChangeMode.LAZY);
        filterClients.addValueChangeListener(e -> updateList());

        var toolbar = new HorizontalLayout(filterClients);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        clientInfoGrid.setItems(clientService.getClientInfoByCompanyVatNumber(filterClients.getValue()));
    }
}
