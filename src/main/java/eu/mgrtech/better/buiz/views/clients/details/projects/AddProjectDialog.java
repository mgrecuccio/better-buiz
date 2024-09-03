package eu.mgrtech.better.buiz.views.clients.details.projects;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.events.project.AddProjectEvent;
import org.springframework.stereotype.Component;

public class AddProjectDialog extends Dialog {

    private static final String CANCEL_BTN_LABEL = "Cancel";
    private static final String SAVE_PROJECT_BTN_LABEL = "Save";
    private static final String NEW_PROJECT_HEADER_TITLE = "New Project";
    private static final String COMPANY_LABEL = "Company";
    private static final String JOB_TITLE = "Job Title";
    private static final String JOB_DESCRIPTION_LABEL = "Job Description";
    private static final String JOB_TYPE_LABEL = "Job Type";
    private static final String JOB_ADDRESS_LABEL = "Job Address";
    private static final String START_DATE_LABEL = "Start date";
    private static final String END_DATE_LABEL = "End date";

    TextField company = new TextField(COMPANY_LABEL);
    TextField jobTitle = new TextField(JOB_TITLE);
    TextArea jobDescription = new TextArea(JOB_DESCRIPTION_LABEL);
    TextField jobType = new TextField(JOB_TYPE_LABEL);
    TextField jobAddress = new TextField(JOB_ADDRESS_LABEL);
    DatePicker startDate = new DatePicker(START_DATE_LABEL);
    DatePicker endDate = new DatePicker(END_DATE_LABEL);

    Button cancelButton;
    Button saveButton;

    Binder<Project> projectBinder = new BeanValidationBinder<>(Project.class);

    public AddProjectDialog() {
        addClassName("add-project-dialog");
        projectBinder.bindInstanceFields(this);
        configureAddProjectDialog();
    }

    private void configureAddProjectDialog() {
        setHeaderTitle(NEW_PROJECT_HEADER_TITLE);

        createFooter();

        VerticalLayout dialogLayout = createDialogLayout();
        add(dialogLayout);
        setModal(false);
        setDraggable(false);
    }

    private VerticalLayout createDialogLayout() {
        var fieldLayout = new VerticalLayout(company, jobTitle, jobDescription, jobType, jobAddress, startDate, endDate);

        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        fieldLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return fieldLayout;
    }

    private void createFooter() {
        cancelButton = new Button(CANCEL_BTN_LABEL, e -> close());
        saveButton = new Button(SAVE_PROJECT_BTN_LABEL, e -> validateAndSave());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getFooter().add(cancelButton);
        getFooter().add(saveButton);
    }

    public void setProject(Project project) {
        projectBinder.setBean(project);
    }

    private void validateAndSave() {
        if (projectBinder.isValid()) {
            fireEvent(new AddProjectEvent(this, projectBinder.getBean()));
            close();
        }
    }

    public Registration addProjectListener(ComponentEventListener<AddProjectEvent> listener) {
        return addListener(AddProjectEvent.class, listener);
    }
}
