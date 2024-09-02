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

    private final LoginOverlay loginForm = new LoginOverlay();
    private final PasswordRecoveryDialog passwordRecoveryDialog = new PasswordRecoveryDialog();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        loginForm.setTitle(APPLICATION_NAME);
        loginForm.setDescription(APPLICATION_DESCRIPTION);
        loginForm.setOpened(true);
        loginForm.getElement().setAttribute("no-autofocus", "");
        loginForm.setAction("login");
        loginForm.addForgotPasswordListener(e -> passwordRecoveryDialog.open());

        add(loginForm);
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
