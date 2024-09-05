package eu.mgrtech.better.buiz.views.finance;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import eu.mgrtech.better.buiz.entities.Transaction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionsListTest {

    private TransactionsList transactionsList;
    private List<Transaction> transactions;

    @BeforeEach
    public void setUp() {
        transactions = List.of(
                new Transaction(UUID.randomUUID().toString(), "First transaction", "-778.50 €", "OUT", "11-07-2024 15:02"),
                new Transaction(UUID.randomUUID().toString(), "Second transaction", "1000.00 €", "IN", "11-07-2024 15:02")
        );
        transactionsList = new TransactionsList(transactions);
    }

    @Test
    public void testThatTheToolBarIsVisibleAndTheFilterButtonIsShown() {
        var toolbar = transactionsList.toolbar;

        assertNotNull(toolbar);
        assertTrue(toolbar.isVisible());

        var toolbarComponentAt = toolbar.getComponentAt(0);
        assertTrue(toolbarComponentAt instanceof TextField);

        var filterTransactions = (TextField) toolbarComponentAt;
        assertTrue(filterTransactions.isVisible());
        assertEquals("Search latest transactions...", filterTransactions.getPlaceholder());
    }

    @Test
    public void testThatTheTransactionGridIsPopulated() {
        var transactionsGrid = transactionsList.transactionsGrid;
        assertTrue(transactionsGrid.isVisible());

        var columns = transactionsGrid.getColumns();
        assertEquals(5, columns.size());

        var id = columns.get(0);
        assertEquals("id", id.getKey());

        var description = columns.get(1);
        assertEquals("description", description.getKey());

        var amount = columns.get(2);
        assertEquals("amount", amount.getKey());

        var intermediaryEMail = columns.get(3);
        assertEquals("creationDate", intermediaryEMail.getKey());

        var creationDate = columns.get(4);
        assertEquals("Type", creationDate.getHeaderText());

        var shownTransactions = transactionsGrid.getGenericDataView().getItems().toList().size();
        assertEquals(transactions.size(), shownTransactions);
    }

    @Test
    public void testThatTheGridIsUpdatedWhenTheFilterChanges() {
        var filterTransactions = transactionsList.filterTransactions;
        filterTransactions.setValue("A wrong value");

        var transactionsGrid = transactionsList.transactionsGrid;
        var shownTransactions = transactionsGrid.getGenericDataView().getItems().toList();
        assertEquals(0, shownTransactions.size());

        filterTransactions.setValue("First");
        shownTransactions = transactionsGrid.getGenericDataView().getItems().toList();
        assertEquals(1, shownTransactions.size());
        assertEquals(transactions.get(0), shownTransactions.get(0));

        filterTransactions.setValue("Second");
        shownTransactions = transactionsGrid.getGenericDataView().getItems().toList();
        assertEquals(1, shownTransactions.size());
        assertEquals(transactions.get(1), shownTransactions.get(0));

        filterTransactions.setValue("");
        shownTransactions = transactionsGrid.getGenericDataView().getItems().toList();
        assertEquals(2, shownTransactions.size());
        assertEquals(transactions, shownTransactions);
    }
}