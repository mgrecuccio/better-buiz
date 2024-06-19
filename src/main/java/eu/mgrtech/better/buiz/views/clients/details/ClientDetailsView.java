package eu.mgrtech.better.buiz.views.clients.details;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.*;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import eu.mgrtech.better.buiz.views.MainLayout;

import static java.awt.AWTEventMulticaster.add;

@PageTitle("Client Details")
@Route(value = "clients/:clientId?", layout = MainLayout.class)
public class ClientDetailsView extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    private String clientId;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String clientId) {
        if (clientId.isEmpty()) {
            throw new IllegalArgumentException("Invalid client ID");
        }
        this.clientId = clientId;
    }

    public ClientDetailsView() {
        Tab details = new Tab("Contracts");
        Tab payment = new Tab("Projects");

        Tabs tabs = new Tabs(details, payment);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        tabs.setWidthFull();
        getContent().add(tabs);
        getContent().setWidthFull();
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);
    }
}
