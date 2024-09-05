package eu.mgrtech.better.buiz.views.authentication;

import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login | Better Buiz")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private static final String APPLICATION_NAME = "BetterBuiz";
    private static final String APPLICATION_DESCRIPTION = "Built with ♥ by MgrTech";

    LoginOverlay loginForm = new LoginOverlay();
    PasswordRecoveryDialog passwordRecoveryDialog = new PasswordRecoveryDialog();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();

        passwordRecoveryDialog.addClassName("password-recovery-dialog");

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        configureLoginForm();

        add(loginForm);
    }

    private void configureLoginForm() {
        loginForm.addClassName("login-form");

        loginForm.setTitle(APPLICATION_NAME);
        loginForm.setDescription(APPLICATION_DESCRIPTION);
        loginForm.setOpened(true);
        loginForm.getElement().setAttribute("no-autofocus", "");
        loginForm.setAction("login");
        loginForm.addForgotPasswordListener(e -> passwordRecoveryDialog.open());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                           .getQueryParameters()
                           .getParameters()
                           .containsKey("error")) {
            loginForm.setError(true);
        }
    }
}
