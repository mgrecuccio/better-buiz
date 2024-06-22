package eu.mgrtech.better.buiz.views.clients.events;

import com.vaadin.flow.component.ComponentEvent;
import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.views.clients.ClientForm;
import lombok.Getter;

@Getter
public abstract class ClientFormEvent extends ComponentEvent<ClientForm> {

    private Client client;

    public ClientFormEvent(ClientForm source, Client client) {
        super(source, false);
        this.client = client;
    }
}
