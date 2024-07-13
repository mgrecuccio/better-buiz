package eu.mgrtech.better.buiz.events.client;

import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.views.clients.ClientForm;

public final class SaveOrUpdateClientEvent extends ClientEvent {

    public SaveOrUpdateClientEvent(ClientForm source, Client client) {
        super(source, client);
    }
}
