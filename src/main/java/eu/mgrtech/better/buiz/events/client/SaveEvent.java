package eu.mgrtech.better.buiz.events.client;

import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.views.clients.ClientForm;

public final class SaveEvent extends ClientFormEvent {

    public SaveEvent(ClientForm source, Client client) {
        super(source, client);
    }
}
