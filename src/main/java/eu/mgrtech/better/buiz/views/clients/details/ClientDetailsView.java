package eu.mgrtech.better.buiz.views.clients.details;

import org.apache.logging.log4j.util.Strings;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import eu.mgrtech.better.buiz.services.ProjectService;
import eu.mgrtech.better.buiz.views.MainLayout;
import eu.mgrtech.better.buiz.views.clients.details.contracts.ContractTab;
import eu.mgrtech.better.buiz.views.clients.details.invoices.InvoiceTab;
import eu.mgrtech.better.buiz.views.clients.details.projects.ProjectsTab;

@PageTitle("Client Details")
@Route(value = "clients/:clientId?", layout = MainLayout.class)
public class ClientDetailsView extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    private static final String CONTRACTS_TAB_TITLE = "Contracts";
    private static final String INVOICES_TAB_TITLE = "Invoices";
    private static final String PROJECTS_TAB_TITLE = "Projects";

    private final ProjectService projectService;
    private String clientId;

    private ContractTab contractTab;
    private ProjectsTab projectsTab;
    private InvoiceTab invoiceTab;

    TabSheet tabSheet;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String clientId) {
        if (Strings.isEmpty(clientId)) {
            throw new IllegalArgumentException("Invalid client ID");
        }
        this.clientId = clientId;
    }

    public ClientDetailsView(ProjectService projectService) {
        addClassName("client-details-view");
        this.projectService = projectService;

        tabSheet = configureTabs();

        getContent().add(tabSheet);
        getContent().setWidthFull();
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);
    }

    private TabSheet configureTabs() {
        TabSheet tabSheet = new TabSheet();

        contractTab = new ContractTab();
        invoiceTab = new InvoiceTab();
        projectsTab = new ProjectsTab(projectService, clientId);

        tabSheet.add(CONTRACTS_TAB_TITLE, contractTab);
        tabSheet.add(INVOICES_TAB_TITLE, invoiceTab);
        tabSheet.add(PROJECTS_TAB_TITLE, projectsTab);

        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);
        tabSheet.setWidthFull();
        return tabSheet;
    }
}
