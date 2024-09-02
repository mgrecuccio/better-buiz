package eu.mgrtech.better.buiz.views.authentication;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;

public class PasswordRecoveryDialog extends ConfirmDialog {

    private final String DIALOG_TITLE = "Password Recovery";
    private final String CONFIRM_SEND_RECOVERY_PASSWORD_EMAIL = "Enter your email address below and we'll send you a link to reset your password.";
    private final String SEND_RECOVERY_PWD_BTN_LABEL = "Send link";

    private final VerticalLayout contentLayout = new VerticalLayout();
    private final EmailField recoveryEmailField = new EmailField();

    public PasswordRecoveryDialog() {
        addClassName("password-recovery-dialog");
        setHeader(DIALOG_TITLE);

        setCancelable(true);
        addCancelListener(event -> close());

        configureContentLayout();

        setConfirmText(SEND_RECOVERY_PWD_BTN_LABEL);
        setConfirmButtonTheme("error primary");
        addConfirmListener(event -> sendLink());

        add(contentLayout);
    }

    private void sendLink() {
        var linkSentNotification = Notification.show("The link to recover your password has been sent.");
        linkSentNotification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        linkSentNotification.setPosition(Notification.Position.TOP_CENTER);
    }

    private void configureContentLayout() {
        recoveryEmailField.getStyle().set("width", "80%");
        contentLayout.add(new Paragraph(CONFIRM_SEND_RECOVERY_PASSWORD_EMAIL), recoveryEmailField);

        contentLayout.setMargin(false);
        contentLayout.setSpacing(false);
        contentLayout.getThemeList().add("spacing-xs");
    }
}
