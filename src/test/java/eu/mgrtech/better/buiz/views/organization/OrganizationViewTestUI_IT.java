package eu.mgrtech.better.buiz.views.organization;

import org.junit.jupiter.api.Test;

import com.github.mvysny.kaributesting.v10.LocatorJ;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;

import eu.mgrtech.better.buiz.views.UITestBase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganizationViewTestUI_IT extends UITestBase {

    @Test
    public void testThatTheViewIsNotAccessibleForNonLoggedUsers() {
        UI.getCurrent().navigate(OrganizationView.class);

        var error = assertThrows(AssertionError.class, () -> LocatorJ._get(H2.class, spec -> spec.withClasses("view-title")));
        assertTrue(error.getMessage().contains("/login: No visible H2 in UI[] matching H2 and classes='view-title'"));
    }
}
