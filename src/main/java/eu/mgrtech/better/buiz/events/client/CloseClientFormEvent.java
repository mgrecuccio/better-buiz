package eu.mgrtech.better.buiz.events.client;

import eu.mgrtech.better.buiz.views.clients.ClientForm;

public final class CloseClientFormEvent extends ClientEvent {

    public CloseClientFormEvent(ClientForm source) {
        super(source, null);
    }
}
