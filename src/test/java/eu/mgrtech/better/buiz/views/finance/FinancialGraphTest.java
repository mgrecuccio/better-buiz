package eu.mgrtech.better.buiz.views.finance;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.storedobject.chart.BarChart;

import eu.mgrtech.better.buiz.entities.FinancialData;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FinancialGraphTest {

    private FinancialData financialData;
    private FinancialGraph financialGraph;

    @BeforeEach
    public void setUp() {
        /*financialData = new FinancialData(
                List.of("January", "February"),
                List.of(1000.00d, 1500.00d),
                List.of(2000.00d, 500.00d)
        );

        financialGraph = new FinancialGraph(financialData);

        // to be tested with https://github.com/mvysny/karibu-testing
         */
    }

    @Test
    public void theGraphIsDrawnWithTheInputDataTest() {
    }

}