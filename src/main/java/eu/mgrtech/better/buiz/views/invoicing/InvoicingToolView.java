package eu.mgrtech.better.buiz.views.invoicing;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import eu.mgrtech.better.buiz.views.MainLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.ActionsButtonLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.InvoiceEntryLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.InvoiceHeaderLayout;
import eu.mgrtech.better.buiz.views.invoicing.layout.InvoiceInfoLayout;
import jakarta.annotation.security.PermitAll;

@PageTitle("Invoicing Tool")
@Route(value = "invoicing-tool", layout = MainLayout.class)
@PermitAll
public class InvoicingToolView extends Composite<VerticalLayout> {

    private final InvoiceHeaderLayout invoiceHeaderLayout = new InvoiceHeaderLayout();
    private final InvoiceEntryLayout invoiceEntryLayout = new InvoiceEntryLayout();
    private final ActionsButtonLayout actionsButtonLayout = new ActionsButtonLayout();
    private final InvoiceInfoLayout invoiceInfoLayout = new InvoiceInfoLayout();

    public InvoicingToolView() {
        addClassName("invoicing-tool-view");

        getContent().add(
                invoiceHeaderLayout,
                invoiceInfoLayout,
                invoiceEntryLayout,
                actionsButtonLayout
        );
    }
}
