package eu.mgrtech.better.buiz.events.client;

import eu.mgrtech.better.buiz.views.clients.ClientForm;

public final class CloseEvent extends ClientFormEvent {

    public CloseEvent(ClientForm source) {
        super(source, null);
    }
}
