package eu.mgrtech.better.buiz.events.client;

import com.vaadin.flow.component.ComponentEvent;
import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.views.clients.ClientForm;
import lombok.Getter;

@Getter
public abstract sealed class ClientEvent extends ComponentEvent<ClientForm> permits CloseClientFormEvent, SaveOrUpdateClientEvent {

    private Client client;

    public ClientEvent(ClientForm source, Client client) {
        super(source, false);
        this.client = client;
    }
}


