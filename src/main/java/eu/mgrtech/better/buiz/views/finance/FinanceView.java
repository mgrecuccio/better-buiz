package eu.mgrtech.better.buiz.views.finance;

import java.util.List;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import eu.mgrtech.better.buiz.entities.FinanceSummary;
import eu.mgrtech.better.buiz.services.FinanceService;
import eu.mgrtech.better.buiz.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@PageTitle("Finance | Better Buiz")
@Route(value = "finances", layout = MainLayout.class)
@PermitAll
public class FinanceView extends Composite<VerticalLayout> {

    private final String orgId = "id-1";
    private static final String FISCAL_YEAR_BOX_TITLE = "Fiscal Year";

    private final FinanceService financeService;
    private final List<String> fiscalYears;
    private String currentYear;

    HorizontalLayout graphicLayout = new HorizontalLayout();
    HorizontalLayout toolbar = new HorizontalLayout();
    Div summaryDiv = new Div();
    FinancialGraph financialGraph;
    TransactionsList transactionsList;

    public FinanceView(FinanceService financeService) {
        addClassName("finance-view");

        this.financeService = financeService;
        fiscalYears = financeService.getFiscalYearsForOrganization(orgId);
        currentYear = fiscalYears.get(0);

        configureToolbar();
        configureGraphicLayout();
        configureTransactionsList();

        getContent().add(toolbar, graphicLayout, transactionsList);
    }

    private void configureTransactionsList() {
        transactionsList = new TransactionsList(financeService.getTransactionsFor(orgId));
    }

    private void configureToolbar() {
        toolbar.addClassName("toolbar");

        Select<String> fiscalYearBox = new Select<>();
        fiscalYearBox.setLabel(FISCAL_YEAR_BOX_TITLE);
        fiscalYearBox.setItems(fiscalYears);
        fiscalYearBox.setValue(currentYear);
        fiscalYearBox.addValueChangeListener(e -> reloadGraph(e.getValue()));

        toolbar.add(fiscalYearBox);
    }

    private void reloadGraph(String fiscalYear) {
        graphicLayout.remove(financialGraph);
        drawGraph(fiscalYear);
        graphicLayout.add(financialGraph, summaryDiv);
    }

    private void configureGraphicLayout() {
        graphicLayout.addClassName("graphic-layout");
        graphicLayout.setWidthFull();
        graphicLayout.setHeight("50%");

        configureSummaryDiv();
        drawGraph(currentYear);

        graphicLayout.add(financialGraph, summaryDiv);
    }

    private void configureSummaryDiv() {
        summaryDiv.addClassName("summary");

        FinanceSummary summary = financeService.getSummaryByOrganization(orgId);

        H2 summaryTitle = new H2(summary.getBankAccountName());
        summaryTitle.addClassName("title");

        H4 summaryIban = new H4(summary.getBankAccountNumber());
        summaryIban.addClassName("iban");

        H3 summaryAmount = new H3(summary.getBankAccountBalance());
        summaryAmount.addClassName("amount");

        summaryDiv.add(summaryTitle, summaryIban, summaryAmount);
    }

    private void drawGraph(String fiscalYear) {
        financialGraph = new FinancialGraph(financeService.getFinancialDataByOrganizationAndFiscalYear(orgId, fiscalYear));
        financialGraph.draw();
    }
}
