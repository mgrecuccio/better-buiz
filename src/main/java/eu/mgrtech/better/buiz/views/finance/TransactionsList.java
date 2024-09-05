package eu.mgrtech.better.buiz.views.finance;

import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import eu.mgrtech.better.buiz.entities.Transaction;

public class TransactionsList extends VerticalLayout {

    private static final String LATEST_TRANSACTIONS_TITLE = "Latest Transactions";
    public static final String SEARCH_LATEST_TRANSACTIONS_PLACEHOLDER = "Search latest transactions...";

    private final String ID = "id";
    private final String DESCRIPTION = "description";
    private final String AMOUNT = "amount";
    private final String TYPE = "Type";
    private final String CREATION_DATE = "creationDate";

    private final List<Transaction> transactions;

    H2 transactionListTitle = new H2(LATEST_TRANSACTIONS_TITLE);
    Grid<Transaction> transactionsGrid = new Grid<>(Transaction.class);
    TextField filterTransactions = new TextField();
    HorizontalLayout toolbar = new HorizontalLayout(filterTransactions);

    public TransactionsList(List<Transaction> transactions) {
        this.transactions = transactions;
        addClassName("transactions-list");

        configureToolbar();
        configureTransactionGrid();

        updateList();

        add(transactionListTitle, toolbar, transactionsGrid);
    }

    private void configureToolbar() {
        toolbar.setWidthFull();

        filterTransactions.addClassName("filter-transactions");
        filterTransactions.setPlaceholder(SEARCH_LATEST_TRANSACTIONS_PLACEHOLDER);
        filterTransactions.setClearButtonVisible(true);
        filterTransactions.setValueChangeMode(ValueChangeMode.LAZY);
        filterTransactions.addValueChangeListener(e -> updateList());
    }

    private void updateList() {
        transactionsGrid.setItems(filter(filterTransactions.getValue()));
    }

    private List<Transaction> filter(String filter) {
        if (Strings.isBlank(filterTransactions.getValue())) {
            return transactions;
        }
        return transactions.stream()
                           .filter(transaction -> transaction.getId().contains(filter) ||
                                                  transaction.getDescription().toLowerCase().contains(filter.toLowerCase())
                           )
                           .toList();
    }

    private void configureTransactionGrid() {
        transactionsGrid.addClassNames("transactions-grid");
        transactionsGrid.setAllRowsVisible(true);
        transactionsGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        transactionsGrid.setWidthFull();
        transactionsGrid.setColumns(ID, DESCRIPTION, AMOUNT, CREATION_DATE);
        transactionsGrid.addComponentColumn(transaction -> createTypeIcon(transaction.getType())).setHeader(TYPE);
        transactionsGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component createTypeIcon(String type) {
        return type.equals("IN") ? VaadinIcon.ARROW_BACKWARD.create() : VaadinIcon.ARROW_FORWARD.create();
    }
}
