package eu.mgrtech.better.buiz.views.invoicing;

import com.github.mvysny.kaributesting.v10.BasicUtilsKt;
import com.github.mvysny.kaributesting.v10.LocatorJ;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import eu.mgrtech.better.buiz.entities.InvoiceEntry;
import eu.mgrtech.better.buiz.views.UITestBase;
import eu.mgrtech.better.buiz.views.invoicing.layout.ActionsButtonLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.InvoiceEntryLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.InvoiceHeaderLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.InvoiceInfoLayout;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoicingToolViewTestUI_IT extends UITestBase {

    @Test
    public void testThatTheViewIsNotAccessibleForNonLoggedUsers() {
        UI.getCurrent().navigate(InvoicingToolView.class);

        var error = assertThrows(AssertionError.class, () -> LocatorJ._get(InvoiceHeaderLayout.class, spec -> spec.withClasses("invoice-header")));
        assertTrue(error.getMessage().contains("/login: No visible InvoiceHeaderLayout in UI[] matching InvoiceHeaderLayout and classes='invoice-header'"));
    }

    @Test
    public void testThatTheInvoiceHeaderIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var invoiceHeaderLayout = LocatorJ._get(InvoiceHeaderLayout.class, spec -> spec.withClasses("invoice-header"));
        assertNotNull(invoiceHeaderLayout);
    }

    @Test
    public void testThatTheInvoiceInfoLayoutIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var invoiceInfoLayout = LocatorJ._get(InvoiceInfoLayout.class, spec -> spec.withClasses("invoice-info"));
        assertNotNull(invoiceInfoLayout);
    }

    @Test
    public void testThatTheInvoiceEntryLayoutIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var invoiceEntryLayout = LocatorJ._get(InvoiceEntryLayout.class, spec -> spec.withClasses("invoice-entry"));
        assertNotNull(invoiceEntryLayout);
    }

    @Test
    public void testThatTheActionButtonsLayoutIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var actionsButtonLayout = LocatorJ._get(ActionsButtonLayout.class, spec -> spec.withClasses("actions-button"));
        assertNotNull(actionsButtonLayout);
    }

    @Test
    public void testThatWhenSendByEmailButtonIsPressedTheDialogIsOpened() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        assertThrows(AssertionError.class, () -> LocatorJ._get(ActionsButtonLayout.SendEmailDialog.class, spec -> spec.withClasses("send-email-dialog")));

        var sendByEmailBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("send-by-email"));
        sendByEmailBtn.click();

        ActionsButtonLayout.SendEmailDialog sendEmailDialog = LocatorJ._get(ActionsButtonLayout.SendEmailDialog.class, spec -> spec.withClasses("send-email-dialog"));
        assertTrue(sendEmailDialog.isOpened());
    }

    @Test
    public void testThatTheDialogIsClosedWhenCancelButtonIsPressed() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        assertThrows(AssertionError.class, () -> LocatorJ._get(ActionsButtonLayout.SendEmailDialog.class, spec -> spec.withClasses("send-email-dialog")));

        var sendByEmailBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("send-by-email"));
        sendByEmailBtn.click();

        ActionsButtonLayout.SendEmailDialog sendEmailDialog = LocatorJ._get(ActionsButtonLayout.SendEmailDialog.class, spec -> spec.withClasses("send-email-dialog"));
        assertTrue(sendEmailDialog.isOpened());

        sendEmailDialog.cancelButton.click();
        assertFalse(sendEmailDialog.isOpened());
    }

    @Test
    public void testThatSuccessNotificationIsShownWhenReportSentToValidEmailAddress() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var sendByEmailBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("send-by-email"));
        sendByEmailBtn.click();

        ActionsButtonLayout.SendEmailDialog sendEmailDialog = LocatorJ._get(ActionsButtonLayout.SendEmailDialog.class, spec -> spec.withClasses("send-email-dialog"));

        EmailField email = sendEmailDialog.email;
        email.setValue("test@email.com");

        sendEmailDialog.sendButton.click();

        var successNotification = LocatorJ._get(Notification.class, spec -> spec.withClasses("success-notification"));
        assertTrue(successNotification.isOpened());

        assertThrows(AssertionError.class, () -> LocatorJ._get(Notification.class, spec -> spec.withClasses("failure-notification")));
    }

    @Test
    public void testThatFailureNotificationIsShownWhenReportSentToInvalidEmailAddress() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var sendByEmailBtn = LocatorJ._get(Button.class, spec -> spec.withClasses("send-by-email"));
        sendByEmailBtn.click();

        ActionsButtonLayout.SendEmailDialog sendEmailDialog = LocatorJ._get(ActionsButtonLayout.SendEmailDialog.class, spec -> spec.withClasses("send-email-dialog"));

        EmailField email = sendEmailDialog.email;
        email.setValue("invalidEmail");

        sendEmailDialog.sendButton.click();

        var failureNotification = LocatorJ._get(Notification.class, spec -> spec.withClasses("failure-notification"));
        assertTrue(failureNotification.isOpened());

        assertThrows(AssertionError.class, () -> LocatorJ._get(Notification.class, spec -> spec.withClasses("success-notification")));
    }

    @Test
    public void testThatTheInvoiceEntryGridIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var entriesGrid = LocatorJ._get(Grid.class, spec -> spec.withClasses("entry-grid"));
        assertNotNull(entriesGrid);

        assertEquals(5, entriesGrid.getColumns().size());

        assertTrue(entriesGrid.getColumnByKey("description").isVisible());
        assertTrue(entriesGrid.getColumnByKey("unit").isVisible());
        assertTrue(entriesGrid.getColumnByKey("unitPrice").isVisible());
        assertTrue(entriesGrid.getColumnByKey("vat").isVisible());
        assertTrue(entriesGrid.getColumnByKey("amount").isVisible());
    }

    @Test
    public void testThatNewEntryIsAddedWhenAddIconIsPressed() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var entriesGrid = LocatorJ._get(Grid.class, spec -> spec.withClasses("entry-grid"));

        List<InvoiceEntry> entries = getInvoiceEntries(entriesGrid);
        assertEquals(1, entries.size());

        InvoiceEntry selectedEntry = entries.getFirst();
        entriesGrid.select(selectedEntry);

        var addEntryIcon = LocatorJ._get(SvgIcon.class, spec -> spec.withClasses("add-entry-icon"));
        BasicUtilsKt._fireDomEvent(addEntryIcon, "click");

        List<InvoiceEntry> updatedEntries = entriesGrid.getGenericDataView().getItems().toList();
        assertEquals(2, updatedEntries.size());
    }

    @Test
    public void testThatEntryIsDeletedWhenRemoveIconIsPressed() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var entriesGrid = LocatorJ._get(Grid.class, spec -> spec.withClasses("entry-grid"));

        List<InvoiceEntry> entries = getInvoiceEntries(entriesGrid);
        assertEquals(1, entries.size());

        var deleteEntryIcon = LocatorJ._get(SvgIcon.class, spec -> spec.withClasses("delete-entry-icon"));
        BasicUtilsKt._fireDomEvent(deleteEntryIcon, "click");

        List<InvoiceEntry> updatedEntries = entriesGrid.getGenericDataView().getItems().toList();
        assertTrue(updatedEntries.isEmpty());
    }

    @Test
    public void testThatAmountIsCalculatedWhenTheEntryIsPersisted() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(InvoicingToolView.class);

        var entriesGrid = LocatorJ._get(Grid.class, spec -> spec.withClasses("entry-grid"));
        List<InvoiceEntry> entries = getInvoiceEntries(entriesGrid);
        InvoiceEntry selectedEntry = entries.getFirst();
        entriesGrid.select(selectedEntry);

        var totalAmount = LocatorJ._get(NumberField.class, spec -> spec.withClasses("amount-with-vat-field"));
        assertTrue(totalAmount.isReadOnly());
        assertNull(totalAmount.getValue());

        var unit = LocatorJ._get(NumberField.class, spec -> spec.withClasses("units"));
        var vat = LocatorJ._get(NumberField.class, spec -> spec.withClasses("vat"));
        var unitPrice = LocatorJ._get(NumberField.class, spec -> spec.withClasses("unit-price"));

        unit.setValue(1d);
        vat.setValue(1d);
        unitPrice.setValue(1d);

        var addEntryIcon = LocatorJ._get(SvgIcon.class, spec -> spec.withClasses("add-entry-icon"));
        BasicUtilsKt._fireDomEvent(addEntryIcon, "click");

        assertEquals(1.01d, selectedEntry.getAmount());
        assertEquals(1.01d, totalAmount.getValue());
    }

    private static @NotNull List<InvoiceEntry> getInvoiceEntries(Grid entriesGrid) {
        return entriesGrid.getGenericDataView().getItems().toList();
    }
}