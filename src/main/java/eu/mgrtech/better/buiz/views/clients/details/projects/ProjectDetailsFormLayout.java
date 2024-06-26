package eu.mgrtech.better.buiz.views.clients.details.projects;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.textfield.TextField;
import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.services.ProjectService;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.stream.Stream;

public class ProjectDetailsFormLayout extends FormLayout {

    private final SvgIcon editIcon = LineAwesomeIcon.EDIT.create();
    private final SvgIcon saveChangesIcon = LineAwesomeIcon.SAVE.create();
    private final SvgIcon cancelChangesIcon = LineAwesomeIcon.UNDO_SOLID.create();
    private final TextField clientField = new TextField("Client");
    private final TextField addressField = new TextField("Address");
    private final TextField roleField = new TextField("Role");
    private final TextField typeField = new TextField("Type");
    private final TextField descriptionField = new TextField("Description");
    private final DatePicker startDateField = new DatePicker("Start date");
    private final DatePicker endDateField = new DatePicker("End date");

    private Project project;

    public ProjectDetailsFormLayout() {
        Stream.of(clientField, addressField, typeField, roleField,
                        descriptionField)
                .forEach(field -> {
                    field.setReadOnly(true);
                    add(field);
                });
        startDateField.setReadOnly(true);
        endDateField.setReadOnly(true);
        add(startDateField, endDateField);

        editIcon.addClickListener(e -> updateEditFields());
        editIcon.setTooltipText("Edit project details");
        add(editIcon);

        saveChangesIcon.setVisible(false);
        saveChangesIcon.addClickListener(svgIconClickEvent -> saveEditEvent());
        saveChangesIcon.setTooltipText("Save changes");
        add(saveChangesIcon);

        cancelChangesIcon.setVisible(false);
        cancelChangesIcon.addClickListener(svgIconClickEvent -> cancelEditEvent());
        cancelChangesIcon.setTooltipText("Forget changes");
        add(cancelChangesIcon);

        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
    }

    public void setProject(Project project) {
        this.project = project;
        clientField.setValue(project.getClientName());
        addressField.setValue(project.getProjectDetails().getAddress());
        roleField.setValue(project.getRole());
        typeField.setValue(project.getProjectDetails().getType());
        descriptionField.setValue(project.getProjectDetails().getDescription());
        startDateField.setValue(project.getProjectDetails().getStartDate());
        endDateField.setValue(project.getProjectDetails().getEndDate());
    }

    public void updateEditFields() {
        Stream.of(clientField, addressField, typeField, roleField, descriptionField)
                .forEach(field -> field.setReadOnly(false));
        startDateField.setReadOnly(false);
        endDateField.setReadOnly(false);

        editIcon.setVisible(false);
        saveChangesIcon.setVisible(true);
        cancelChangesIcon.setVisible(true);
    }

    public
    void cancelEditEvent() {
        Stream.of(clientField, addressField, typeField, roleField, descriptionField)
                .forEach(field -> field.setReadOnly(true));
        startDateField.setReadOnly(true);
        endDateField.setReadOnly(true);
        saveChangesIcon.setVisible(false);
        cancelChangesIcon.setVisible(false);
        editIcon.setVisible(true);

        setProject(project);
    }

    private void saveEditEvent() {
        Stream.of(clientField, addressField, typeField, roleField, descriptionField)
                .forEach(field -> field.setReadOnly(true));
        startDateField.setReadOnly(true);
        endDateField.setReadOnly(true);
        saveChangesIcon.setVisible(false);
        cancelChangesIcon.setVisible(false);
        editIcon.setVisible(true);
        System.out.println("Data saved");
    }
}
