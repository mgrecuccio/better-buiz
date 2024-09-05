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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@PageTitle("Invoicing Tool | Better Buiz")
@Route(value = "invoicing-tool", layout = MainLayout.class)
@PermitAll
@Component
@Scope("prototype")
public class InvoicingToolView extends Composite<VerticalLayout> {

    InvoiceHeaderLayout invoiceHeaderLayout = new InvoiceHeaderLayout();
    InvoiceEntryLayout invoiceEntryLayout = new InvoiceEntryLayout();
    ActionsButtonLayout actionsButtonLayout = new ActionsButtonLayout();
    InvoiceInfoLayout invoiceInfoLayout = new InvoiceInfoLayout();

    public InvoicingToolView() {
        addClassName("invoicing-tool-view");

        invoiceHeaderLayout.addClassName("invoice-header");
        invoiceEntryLayout.addClassName("invoice-entry");
        actionsButtonLayout.addClassName("actions-button");
        invoiceInfoLayout.addClassName("invoice-info");

        getContent().add(
                invoiceHeaderLayout,
                invoiceInfoLayout,
                invoiceEntryLayout,
                actionsButtonLayout
        );
    }
}
