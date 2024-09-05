package eu.mgrtech.better.buiz.views.finance;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.mvysny.kaributesting.v10.LocatorJ;
import com.storedobject.chart.BarChart;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;

import eu.mgrtech.better.buiz.views.UITestBase;
import static org.junit.jupiter.api.Assertions.*;

class FinanceViewTestUI_IT extends UITestBase {

    @Test
    public void testThatTheViewIsNotAccessibleForNonLoggedUsers() {
        UI.getCurrent().navigate(FinanceView.class);

        AssertionError error = assertThrows(AssertionError.class, () -> LocatorJ._get(HorizontalLayout.class, spec -> spec.withClasses("toolbar")));
        assertTrue(error.getMessage().contains("/login: No visible HorizontalLayout in UI[] matching HorizontalLayout and classes='toolbar'"));
    }

    @Test
    public void testThatAToolbarWithTheFiscalYearSelectionIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(FinanceView.class);

        var toolbar = LocatorJ._get(HorizontalLayout.class, spec -> spec.withClasses("toolbar"));
        assertNotNull(toolbar);

        var fiscalYearBox =  (Select<String>)toolbar.getComponentAt(0);

        assertFalse(fiscalYearBox.isEmpty());
        assertEquals("Fiscal Year", fiscalYearBox.getLabel());

        var yearValues = fiscalYearBox.getGenericDataView().getItems().toList();
        assertTrue(yearValues.size() == 3);
    }

    @Test
    public void testThatTheFinancialGraphIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(FinanceView.class);

        var financialGraph = LocatorJ._get(FinancialGraph.class, spec -> spec.withClasses("financial-graph"));
        assertNotNull(financialGraph);

        BarChart chart = financialGraph.chart;
        assertNotNull(chart);
        assertEquals("Expenses", chart.getName());
    }

    @Test
    public void testThatTheFinancialSummaryIsShown() {
        login("user", "password", List.of("USER"));
        UI.getCurrent().navigate(FinanceView.class);

        var financialSummary = LocatorJ._get(Div.class, spec -> spec.withClasses("summary"));
        assertNotNull(financialSummary);

        assertTrue(financialSummary.isVisible());
        assertTrue(financialSummary.getComponentCount() == 3);

        H2 summaryTitle = (H2) financialSummary.getComponentAt(0);
        assertEquals("MGRTECH srl", summaryTitle.getText());

        H4 summaryIban = (H4) financialSummary.getComponentAt(1);
        assertEquals("BE68 5390 0754 7034", summaryIban.getText());

        H3 summaryAmount = (H3) financialSummary.getComponentAt(2);
        assertEquals("11.945,00 €", summaryAmount.getText());
    }
}