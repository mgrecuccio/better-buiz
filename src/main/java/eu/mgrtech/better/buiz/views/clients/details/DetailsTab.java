package eu.mgrtech.better.buiz.views.clients.details;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;

public class DetailsTab extends Div {

    public DetailsTab() {
        addClassName("details-tab");
        Text text = new Text("This is the Details tab content");
        add(text);
    }
}
