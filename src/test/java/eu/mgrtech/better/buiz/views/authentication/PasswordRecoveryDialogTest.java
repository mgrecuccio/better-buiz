package eu.mgrtech.better.buiz.views.authentication;

import org.junit.jupiter.api.Test;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.EmailField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordRecoveryDialogTest {

    private PasswordRecoveryDialog passwordRecoveryDialog = new PasswordRecoveryDialog();

    @Test
    public void testThatTheDialogIsStructuredAsExpected() {
        var contentLayout = passwordRecoveryDialog.contentLayout;

        var paragraph = (Paragraph) contentLayout.getComponentAt(0);
        assertEquals("Enter your email address below and we'll send you a link to reset your password.", paragraph.getText());

        var recoveryEmailField = (EmailField) contentLayout.getComponentAt(1);
        assertTrue(recoveryEmailField.isVisible());
        assertTrue(recoveryEmailField.isEnabled());
    }
}