package eu.mgrtech.better.buiz.views.invoicing;

import java.util.Optional;

import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class InvoiceEntryView extends VerticalLayout {

    final Grid<InvoiceEntry> grid;
    private Optional<Grid.Column<InvoiceEntry>> currentColumn = Optional.empty();
    private Optional<InvoiceEntry> currentItem = Optional.empty();

    public InvoiceEntryView() {
        this.grid = new Grid<>(InvoiceEntry.class, false);
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

        var txtOccurrence = new TextField();
        txtOccurrence.setWidthFull();
        binder.forField(txtOccurrence).bind("occurrence");
        grid.addColumn("occurrence").setEditorComponent(txtOccurrence).setAutoWidth(true);

        grid.setItems(new InvoiceEntry("description", "4"));
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
        add(grid);
    }

}
