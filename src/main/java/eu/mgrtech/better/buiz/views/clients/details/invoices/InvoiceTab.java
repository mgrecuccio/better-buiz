package eu.mgrtech.better.buiz.views.clients.details.invoices;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;

public class InvoiceTab extends Div {

    public InvoiceTab() {
        addClassName("invoices-tab");
        Text text = new Text("This is the Invoices tab content");
        add(text);
    }

}
