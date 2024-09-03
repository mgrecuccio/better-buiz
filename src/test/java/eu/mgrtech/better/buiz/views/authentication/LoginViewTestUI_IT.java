package eu.mgrtech.better.buiz.views.authentication;

import org.junit.jupiter.api.Test;

import com.github.mvysny.kaributesting.v10.LoginFormKt;
import com.github.mvysny.kaributesting.v10.pro.ConfirmDialogKt;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;

import eu.mgrtech.better.buiz.views.UITestBase;
import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginViewTestUI_IT extends UITestBase {

    @Test
    public void testThatTheViewIsAccessibleForAnonymousUsers() {
        UI.getCurrent().navigate(LoginView.class);

        var loginOverlay = _get(LoginOverlay.class, spec -> spec.withClasses("login-form"));
        assertTrue(loginOverlay.isVisible());
        assertTrue(loginOverlay.isForgotPasswordButtonVisible());
    }

    @Test
    public void testThatIsNotVisibleByDefault() {
        UI.getCurrent().navigate(LoginView.class);

        var error = assertThrows(AssertionError.class, () -> _get(PasswordRecoveryDialog.class, spec -> spec.withClasses("password-recovery-dialog")));
        assertTrue(error.getMessage().contains("/login: No visible PasswordRecoveryDialog in UI[] matching PasswordRecoveryDialog and classes='password-recovery-dialog'"));
    }

    @Test
    public void testThatWhenForgotPasswordEventIsFiredTheDialogIsShown() {
        UI.getCurrent().navigate(LoginView.class);
        LoginFormKt._forgotPassword(_get(LoginOverlay.class));

        var passwordRecoveryDialog = _get(PasswordRecoveryDialog.class, spec -> spec.withClasses("password-recovery-dialog"));
        assertTrue(passwordRecoveryDialog.isOpened());
    }

    @Test
    public void testThatWhenTheDialogIsConfirmedTheNotificationIsSent() {
        UI.getCurrent().navigate(LoginView.class);
        LoginFormKt._forgotPassword(_get(LoginOverlay.class));

        var passwordRecoveryDialog = _get(PasswordRecoveryDialog.class, spec -> spec.withClasses("password-recovery-dialog"));
        assertTrue(passwordRecoveryDialog.isOpened());

        ConfirmDialogKt._fireConfirm(_get(PasswordRecoveryDialog.class));

        var notificationLink = _get(Notification.class, spec -> spec.withClasses("notification-link"));
        assertTrue(notificationLink.isVisible());
        assertFalse(passwordRecoveryDialog.isOpened());
    }

    @Test
    public void testThatWhenTheDialogIsClosedNoNotificationIsSent() {
        UI.getCurrent().navigate(LoginView.class);
        LoginFormKt._forgotPassword(_get(LoginOverlay.class));

        var passwordRecoveryDialog = _get(PasswordRecoveryDialog.class, spec -> spec.withClasses("password-recovery-dialog"));
        assertTrue(passwordRecoveryDialog.isOpened());

        ConfirmDialogKt._fireCancel(_get(PasswordRecoveryDialog.class));

        assertThrows(AssertionError.class, () -> _get(Notification.class, spec -> spec.withClasses("notification-link")));
        assertFalse(passwordRecoveryDialog.isOpened());
    }
}