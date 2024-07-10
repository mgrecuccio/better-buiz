package eu.mgrtech.better.buiz.views.finance;

import com.storedobject.chart.BarChart;
import com.storedobject.chart.DataMatrix;
import com.storedobject.chart.DataType;
import com.storedobject.chart.RectangularCoordinate;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.Size;
import com.storedobject.chart.XAxis;
import com.storedobject.chart.YAxis;

import eu.mgrtech.better.buiz.entities.FinancialData;

public class FinancialGraph extends SOChart {

    private static final String INCOMES = "Incomes";
    private static final String EURO = "Euro";
    private static final String EXPENSES = "Expenses";

    private final FinancialData financialData;

    BarChart chart;

    public FinancialGraph(FinancialData financialData) {
        this.financialData = financialData;
    }

    public void draw() {
        setWidth("80%");
        setHeight("100%");

        DataMatrix dataMatrix = new DataMatrix(INCOMES);

        dataMatrix.setColumnNames(financialData.getMonths().toArray(new String[0]));
        dataMatrix.setColumnDataName(INCOMES);

        dataMatrix.setRowDataName(EURO);
        dataMatrix.setRowNames(INCOMES, EXPENSES);

        dataMatrix.addRow(financialData.getIncomes().toArray(new Double[0]));
        dataMatrix.addRow(financialData.getExpenses().toArray(new Double[0]));

        XAxis xAxis = new XAxis(DataType.CATEGORY);
        xAxis.setName(dataMatrix.getColumnDataName());

        YAxis yAxis = new YAxis(DataType.NUMBER);
        yAxis.setName(dataMatrix.getRowDataName());

        RectangularCoordinate rc = new RectangularCoordinate();
        rc.addAxis(xAxis, yAxis);

        for (int i = 0; i < dataMatrix.getRowCount(); i++) {
            chart = new BarChart(dataMatrix.getColumnNames(), dataMatrix.getRow(i));
            chart.setName(dataMatrix.getRowName(i));
            chart.plotOn(rc);
        }

        add(chart);
        rc.getPosition(true).setBottom(Size.percentage(40));
    }
}
