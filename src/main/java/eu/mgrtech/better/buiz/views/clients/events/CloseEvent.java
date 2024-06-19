package eu.mgrtech.better.buiz.views.clients.events;

import eu.mgrtech.better.buiz.views.clients.ClientForm;

public class CloseEvent extends ClientFormEvent {

    public CloseEvent(ClientForm source) {
        super(source, null);
    }
}
