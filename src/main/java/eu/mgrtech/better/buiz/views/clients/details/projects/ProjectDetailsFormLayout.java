package eu.mgrtech.better.buiz.views.clients.details.projects;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.textfield.TextField;
import eu.mgrtech.better.buiz.entities.Project;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class ProjectDetailsFormLayout extends FormLayout {


    public static final String EDIT_PROJECT_DETAILS_TOOLTIP = "Edit project details";
    /*SvgIcon saveChangesIcon = LineAwesomeIcon.SAVE.create();
            SvgIcon cancelChangesIcon = LineAwesomeIcon.UNDO_SOLID.create();*/
    TextField companyProject = new TextField("Company Project");
    TextField jobDescription = new TextField("Job Description");
    TextField jobType = new TextField("Job Type");
    TextField jobAddress = new TextField("Job Address");
    DatePicker startDate = new DatePicker("Start date");
    DatePicker endDate = new DatePicker("End date");

    SvgIcon editIcon;
    SvgIcon cancelChangesIcon;
    SvgIcon saveChangesIcon;

    public ProjectDetailsFormLayout() {
        configureFormFields(true);
        configureActionsIcons();

        add(
            companyProject,
            jobDescription,
            jobType,
            jobAddress,
            startDate,
            endDate,
            editIcon
        );

        setColspan(editIcon, 3);

        /*editIcon.addClickListener(e -> updateEditFields());
        editIcon.setTooltipText("Edit project details");
        add(editIcon);

        saveChangesIcon.setVisible(false);
        saveChangesIcon.addClickListener(svgIconClickEvent -> saveEditEvent());
        saveChangesIcon.setTooltipText("Save changes");
        add(saveChangesIcon);

        cancelChangesIcon.setVisible(false);
        cancelChangesIcon.addClickListener(svgIconClickEvent -> cancelEditEvent());
        cancelChangesIcon.setTooltipText("Forget changes");
        add(cancelChangesIcon);*/

        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
    }

    private void configureActionsIcons() {
        editIcon = LineAwesomeIcon.EDIT.create();
        editIcon.setTooltipText(EDIT_PROJECT_DETAILS_TOOLTIP);
        editIcon.addClickListener(e -> configureFormFields(false));
    }

    private void configureFormFields(boolean readonly) {
        companyProject.setReadOnly(readonly);
        jobType.setReadOnly(readonly);
        jobAddress.setReadOnly(readonly);
        jobDescription.setReadOnly(readonly);
        startDate.setReadOnly(readonly);
        endDate.setReadOnly(readonly);
    }

    public void setProject(Project project) {
        companyProject.setValue(project.getCompanyProject());
        jobType.setValue(project.getJobType());
        jobAddress.setValue(project.getJobAddress());
        jobDescription.setValue(project.getJobDescription());
        startDate.setValue(project.getStartDate());
        endDate.setValue(project.getEndDate());
    }


   /* public void updateEditFields() {
        Stream.of(companyProject, addressField, typeField, roleField, descriptionField)
                .forEach(field -> field.setReadOnly(false));
        startDateField.setReadOnly(false);
        endDateField.setReadOnly(false);

        editIcon.setVisible(false);
        saveChangesIcon.setVisible(true);
        cancelChangesIcon.setVisible(true);
    }

    public void cancelEditEvent() {
        Stream.of(companyProject, addressField, typeField, roleField, descriptionField)
                .forEach(field -> field.setReadOnly(true));
        startDateField.setReadOnly(true);
        endDateField.setReadOnly(true);
        saveChangesIcon.setVisible(false);
        cancelChangesIcon.setVisible(false);
        editIcon.setVisible(true);

        setProject(project);
    }

    private void saveEditEvent() {
        Stream.of(companyProject, addressField, typeField, roleField, descriptionField)
                .forEach(field -> field.setReadOnly(true));
        startDateField.setReadOnly(true);
        endDateField.setReadOnly(true);
        saveChangesIcon.setVisible(false);
        cancelChangesIcon.setVisible(false);
        editIcon.setVisible(true);
        System.out.println("Data saved");
    }*/
}
