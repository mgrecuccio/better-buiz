package eu.mgrtech.better.buiz.views.invoicing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import eu.mgrtech.better.buiz.entities.InvoiceEntry;

public class InvoiceEntryView extends VerticalLayout {

    private List<InvoiceEntry> entries = new ArrayList<>();
    final Grid<InvoiceEntry> grid;
    private Optional<Grid.Column<InvoiceEntry>> currentColumn = Optional.empty();
    private Optional<InvoiceEntry> currentItem = Optional.empty();

    public InvoiceEntryView() {
        setWidth("90%");
        this.grid = new Grid<>(InvoiceEntry.class, false);
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_COMPACT);
        grid.setAllRowsVisible(true);
        var binder = new BeanValidationBinder<>(InvoiceEntry.class); // <1>
        // <2>
        var editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);
        editor.addSaveListener(event -> {
            InvoiceEntry c = event.getItem();
        });
        // <3>
        var descriptionField = new TextField();
        descriptionField.setWidthFull();
        binder.forField(descriptionField).bind("description");
        grid.addColumn("description").setEditorComponent(descriptionField).setAutoWidth(true);

        var unitField = new NumberField();
        unitField.setWidthFull();
        binder.forField(unitField).bind("unit");
        grid.addColumn("unit").setEditorComponent(unitField).setAutoWidth(true);

        var unitPriceField = new NumberField();
        unitPriceField.setWidthFull();
        binder.forField(unitPriceField).bind("unitPrice");
        grid.addColumn("unitPrice").setEditorComponent(unitPriceField).setAutoWidth(true);

        var amountField = new NumberField();
        amountField.setWidthFull();
        amountField.setReadOnly(true);
        binder.forField(amountField).bind("amount");
        grid.addColumn("amount").setAutoWidth(true);

        addInvoiceEntry();

        grid.setItems(entries);
        // <4>
        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(samplePerson -> {
            editor.save();
            if (!editor.isOpen()) {
                grid.getEditor().editItem(samplePerson);
                currentColumn.ifPresent(column -> {
                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
                        focusable.focus();
                    }
                });
            }
        }));
        grid.addCellFocusListener(event -> {
            currentItem = event.getItem();
            currentColumn = event.getColumn();
        });
        add(addRow(), grid);
    }

    private Span addRow() {
        Span span = new Span();
        span.getStyle().set("margin-left", "10px");
        SvgIcon icon = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
        icon.setTooltipText("Add invoice entry");
        icon.addClickListener(iconClickEvent -> addInvoiceEntry());
        icon.getStyle().set("margin-right", "5px");

        SvgIcon deleteIcon = LineAwesomeIcon.MINUS_CIRCLE_SOLID.create();
        deleteIcon.addClickListener(deleteClick -> deleteInvoiceEntry());
        deleteIcon.setTooltipText("Delete invoice entry");

        span.add(icon, deleteIcon);
        return span;
    }

    private void deleteInvoiceEntry() {
        InvoiceEntry toBeDeleted = entries.get(0);
        entries.remove(toBeDeleted);
        grid.setItems(entries);
    }

    private void addInvoiceEntry() {
        entries.add(new InvoiceEntry());
        grid.setItems(entries);
    }
}
