package eu.mgrtech.better.buiz.views.clients;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import eu.mgrtech.better.buiz.services.ClientService;
import eu.mgrtech.better.buiz.views.MainLayout;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Clients")
@Route(value = "clients", layout = MainLayout.class)
public class ClientsView extends Composite<VerticalLayout> {

    public static final String VAT_NUMBER = "vatNumber";
    public static final String NAME = "name";
    public static final String STATUS = "Status";
    public static final String INTERMEDIARY = "intermediary";
    public static final String CONTACT_EMAIL_ADDRESS = "contactEmailAddress";
    private final ClientService clientService;

    Grid<ClientInfo> clientInfoGrid = new Grid<>(ClientInfo.class);

    public ClientsView(ClientService clientService) {
        this.clientService = clientService;
        getContent().setSizeFull();

        configureClientsGrid();

        getContent().add(clientInfoGrid);
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
        Span confirmed = new Span(status.name());
        switch (status) {
            case ClientStatus.ACTIVE:
                confirmed.getElement().getThemeList().add("badge success");
                break;
            case ClientStatus.INACTIVE:
                confirmed.getElement().getThemeList().add("badge error");
                break;
            default:
                confirmed.getElement().getThemeList().add("badge contrast");
        }
        return confirmed;
    }

    private void updateList() {
        clientInfoGrid.setItems(clientService.getClientInfoByCompanyVatNumber(""));
    }
}
