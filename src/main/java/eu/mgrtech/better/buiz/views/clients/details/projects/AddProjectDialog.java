package eu.mgrtech.better.buiz.views.clients.details.projects;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import eu.mgrtech.better.buiz.entities.Project;

public class AddProjectDialog extends Dialog {

    private static final String CANCEL_BTN_LABEL = "Cancel";
    private static final String ADD_PROJECT_BTN_LABEL = "Add project";

    public AddProjectDialog() {
        addClassName("add-project-dialog");
        configureAddProjectDialog();
    }

    private void configureAddProjectDialog() {
        setHeaderTitle("New Project");

        createFooter();

        VerticalLayout dialogLayout = createDialogLayout();
        add(dialogLayout);
        setModal(false);
        setDraggable(false);
    }

    private VerticalLayout createDialogLayout() {
        TextField titleField = new TextField("Title");
        TextArea descriptionArea = new TextArea("Description");

        VerticalLayout fieldLayout = new VerticalLayout(titleField,
                descriptionArea);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        fieldLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return fieldLayout;
    }

    private void createFooter() {
        Button cancelButton = new Button(CANCEL_BTN_LABEL, e -> close());
        Button saveButton = new Button(ADD_PROJECT_BTN_LABEL, e -> close());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getFooter().add(cancelButton);
        getFooter().add(saveButton);
    }

    public void setProject(Project project) {

    }
}
