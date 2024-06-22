package eu.mgrtech.better.buiz.views.clients.events;

import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.views.clients.ClientForm;

public class SaveEvent extends ClientFormEvent {

    public SaveEvent(ClientForm source, Client client) {
        super(source, client);
    }
}
