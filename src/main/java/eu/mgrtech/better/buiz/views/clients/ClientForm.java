package eu.mgrtech.better.buiz.views.clients;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import eu.mgrtech.better.buiz.entities.Client;
import eu.mgrtech.better.buiz.events.client.CloseEvent;
import eu.mgrtech.better.buiz.events.client.SaveEvent;

public class ClientForm extends FormLayout {

    private static final String VAT_NUMBER = "VAT Number";
    private static final String CLIENT_NAME = "Client name";
    private static final String INTERMEDIARY_NAME = "Intermediary name";
    private static final String INTERMEDIARY_EMAIL = "Intermediary email";
    private static final String SAVE_BTN_LABEL = "Save";
    private static final String CANCEL_BTN_LABEL = "Cancel";

    TextField vatNumber = new TextField(VAT_NUMBER);
    TextField name = new TextField(CLIENT_NAME);
    TextField intermediary = new TextField(INTERMEDIARY_NAME);
    EmailField contactEmailAddress = new EmailField(INTERMEDIARY_EMAIL);

    Button save = new Button(SAVE_BTN_LABEL);
    Button close = new Button(CANCEL_BTN_LABEL);

    Binder<Client> clientBinder = new BeanValidationBinder<>(Client.class);

    public ClientForm() {
        addClassName("client-form");
        clientBinder.bindInstanceFields(this);

        add(vatNumber,
            name,
            intermediary,
            contactEmailAddress,
            createButtonsLayout()
        );
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, close);
    }

    private void validateAndSave() {
        if (clientBinder.isValid()) {
            fireEvent(new SaveEvent(this, clientBinder.getBean()));
        }
    }

    public void setClient(Client client) {
        clientBinder.setBean(client);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
