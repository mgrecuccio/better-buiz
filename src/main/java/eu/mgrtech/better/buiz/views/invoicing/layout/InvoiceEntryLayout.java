package eu.mgrtech.better.buiz.views.invoicing.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.grid.editor.EditorSaveEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import eu.mgrtech.better.buiz.entities.InvoiceEntry;

public class InvoiceEntryLayout extends VerticalLayout {

    private static final String DESCRIPTION = "description";
    private static final String UNIT = "unit";
    private static final String UNIT_PRICE = "unitPrice";
    private static final String VAT = "vat";
    private static final String AMOUNT = "amount";
    private static final String ADD_INVOICE_ENTRY_TOOLTIP = "Add invoice entry";
    private static final String DELETE_INVOICE_ENTRY_TOOLTIP = "Delete invoice entry";

    private List<InvoiceEntry> entries = new ArrayList<>();

    private BeanValidationBinder binder = new BeanValidationBinder<>(InvoiceEntry.class);
    private Optional<Grid.Column<InvoiceEntry>> currentColumn = Optional.empty();
    private Optional<InvoiceEntry> currentItem = Optional.empty();
    private Editor<InvoiceEntry> editor;

    Grid<InvoiceEntry> entriesGrid;
    TextField description = new TextField();
    NumberField units = new NumberField();
    NumberField unitPrice = new NumberField();
    NumberField vat = new NumberField();
    NumberField amount = new NumberField();
    Span iconsManagementSection = new Span();
    AmountLayout amountForm = new AmountLayout();

    private boolean addingNewEntry = false;

    public InvoiceEntryLayout() {
        addClassName("invoice-entry-layout");

        entriesGrid = new Grid<>(InvoiceEntry.class, false);
        entriesGrid.setItems(entries);

        configureGrid();
        configureEditor();
        configureSelectionListener();
        configureIconsManagementSection();
        addColumns();
        addInvoiceEntry();

        add(iconsManagementSection, entriesGrid, amountForm);
    }

    private void configureGrid() {
        entriesGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COMPACT);
        entriesGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        entriesGrid.setAllRowsVisible(true);
    }

    private void configureEditor() {
        editor = entriesGrid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        editor.addSaveListener(saveEvent -> saveAndAddNewEntryIfNeeded(saveEvent));
    }

    private void saveAndAddNewEntryIfNeeded(EditorSaveEvent<InvoiceEntry> saveEvent) {
        if(addingNewEntry) {
            addInvoiceEntry();
            addingNewEntry = false;
        }
        validateAndSave(saveEvent.getItem());
    }

    private void validateAndSave(InvoiceEntry entryToPersist) {
        entryToPersist.calculateAmount();
        amountForm.updateAmount(getTotalAmount());
    }

    private void addInvoiceEntry() {
        InvoiceEntry newEntry = new InvoiceEntry();
        entries.add(newEntry);
        entriesGrid.setItems(entries);
    }

    private void configureSelectionListener() {
        entriesGrid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(selectedEntry -> {
            editor.save();
            if (!editor.isOpen()) {
                entriesGrid.getEditor().editItem(selectedEntry);
                currentColumn.ifPresent(column -> {
                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
                        focusable.focus();
                    }
                });
            }
        }));
        entriesGrid.addCellFocusListener(event -> {
            currentItem = event.getItem();
            currentColumn = event.getColumn();
        });
    }

    private void configureIconsManagementSection() {
        iconsManagementSection.addClassName("add-remove-entry-span");

        var addEntry = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
        addEntry.addClassName("add-entry-icon");
        addEntry.setTooltipText(ADD_INVOICE_ENTRY_TOOLTIP);

        addEntry.addClickListener(iconClickEvent -> addNewEntryEvent());

        var deleteEntry = LineAwesomeIcon.MINUS_CIRCLE_SOLID.create();
        deleteEntry.addClickListener(deleteClick -> deleteInvoiceEntry(entriesGrid.getSelectedItems().stream().findFirst()));
        deleteEntry.setTooltipText(DELETE_INVOICE_ENTRY_TOOLTIP);

        iconsManagementSection.add(addEntry, deleteEntry);
    }

    private void addColumns() {
        configureTextColumn(description, DESCRIPTION);
        configureNumericColumn(units, UNIT, null, false);
        configureNumericColumn(unitPrice, UNIT_PRICE, "( € )", false);
        configureNumericColumn(vat, VAT, "( % )", false);
        configureNumericColumn(amount, AMOUNT, "( € )", true);
    }

    private void configureNumericColumn(NumberField field, String propertyName, String headerSuffix, boolean readOnly) {
        field.setWidthFull();
        field.setReadOnly(readOnly);
        binder.forField(field).bind(propertyName);
        Grid.Column<InvoiceEntry> invoiceEntryColumn = entriesGrid.addColumn(propertyName);
        invoiceEntryColumn.setEditorComponent(field).setAutoWidth(true);
        if (!StringUtils.isBlank(headerSuffix)) {
            invoiceEntryColumn.setHeader(propertyName + " " + headerSuffix);
        }
    }

    private void configureTextColumn(TextField field, String propertyName) {
        field.setWidthFull();
        binder.forField(field).bind(propertyName);
        entriesGrid.addColumn(propertyName).setEditorComponent(field).setAutoWidth(true);
    }

    private void addNewEntryEvent() {
        if(entries.isEmpty()) {
            addInvoiceEntry();
        }
        addingNewEntry = true;
        editor.save();
    }

    private void deleteInvoiceEntry(Optional<InvoiceEntry> optInvoiceEntry) {
        InvoiceEntry entryToDelete;
        if (optInvoiceEntry.isPresent()) {
            entryToDelete = optInvoiceEntry.get();
        } else {
            entryToDelete = entries.getLast();
        }
        entries.remove(entryToDelete);
        amountForm.updateAmount(getTotalAmount());
        entriesGrid.setItems(entries);
    }

    private Double getTotalAmount() {
        return entries.stream().map(InvoiceEntry::getAmount).reduce(0d, Double::sum);
    }
}