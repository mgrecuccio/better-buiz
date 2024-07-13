package eu.mgrtech.better.buiz.views.invoicing.layout;

import java.io.File;

import org.vaadin.olli.FileDownloadWrapper;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;

public class ActionsButtonLayout extends HorizontalLayout {

    private static final String GENERATE_PDF_LABEL = "Generate PDF";
    private static final String SEND_BY_EMAIL_LABEL = "Send by Email";
    private static final String FAILURE_NOTIFICATION_MSG = "Please enter a valid email address";
    private static final String SEND_INVOICE_BY_E_MAIL = "Send Invoice by e-mail";
    private final String CANCEL_BUTTON_LABEL = "Cancel";
    private final String SEND_BUTTON_LABEL = "Send";

    private final SendEmailDialog sendEmailDialog = new SendEmailDialog();
    private final Button sendByEmail = new Button(SEND_BY_EMAIL_LABEL);
    private final Button generatePdf = new Button(GENERATE_PDF_LABEL);
    private FileDownloadWrapper generatePdfWrapper;

    public ActionsButtonLayout() {
        addClassName("buttons-layout");

        configureGeneratePdfButton();
        configureSendByEmailButton();

        add(generatePdfWrapper, sendByEmail);
    }

    private void configureGeneratePdfButton() {
        generatePdf.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        generatePdfWrapper = new FileDownloadWrapper("invoice.pdf", new File("src/main/resources/invoice.pdf"));
        generatePdfWrapper.wrapComponent(generatePdf);
    }

    private void configureSendByEmailButton() {
        sendByEmail.addClickListener(e -> sendEmailDialog.open());
        sendByEmail.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }

    private class SendEmailDialog extends Dialog {

        private final EmailField email = new EmailField();

        public SendEmailDialog() {
            addClassName("send-email-dialog");

            setHeaderTitle(SEND_INVOICE_BY_E_MAIL);
            configureFooter();
            add(createDialogLayout());

            setModal(false);
            setDraggable(true);
        }

        private VerticalLayout createDialogLayout() {
            var fieldLayout = new VerticalLayout(email);

            fieldLayout.setSpacing(false);
            fieldLayout.setPadding(false);
            fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            fieldLayout.getStyle().set("width", "300px").set("max-width", "100%");

            return fieldLayout;
        }

        private void configureFooter() {
            var cancelButton = new Button(CANCEL_BUTTON_LABEL, e -> close());
            var sendButton = new Button(SEND_BUTTON_LABEL, e -> sendAndNotify());

            getFooter().add(cancelButton);
            getFooter().add(sendButton);
        }

        private void sendAndNotify() {
            if (email.isEmpty() || email.isInvalid()) {
                showFailureNotification();
            } else {
                showSuccessNotification();
            }
        }

        private void showSuccessNotification() {
            var successNotification = Notification.show("Email sent to " + email.getValue());
            successNotification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            successNotification.setPosition(Notification.Position.TOP_CENTER);
        }

        private void showFailureNotification() {
            var failureNotification = Notification.show(FAILURE_NOTIFICATION_MSG);
            failureNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            failureNotification.setPosition(Notification.Position.TOP_CENTER);
        }
    }
}
