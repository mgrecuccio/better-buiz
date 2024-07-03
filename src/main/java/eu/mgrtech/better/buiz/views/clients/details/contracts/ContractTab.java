package eu.mgrtech.better.buiz.views.clients.details.contracts;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;

public class ContractTab extends Div {

    public ContractTab() {
        addClassName("contract-tab");
        Text text = new Text("This is the Contracts tab content");
        add(text);
    }
}
