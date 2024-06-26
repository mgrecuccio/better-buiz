package eu.mgrtech.better.buiz.views.clients.details.projects;

import java.util.Objects;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.events.project.SaveEvent;

public class ProjectDetailsFormLayout extends FormLayout {

    private static final String EDIT_PROJECT_DETAILS_TOOLTIP = "Edit project details";
    private static final String SAVE_CHANGES_PROJECT_DETAILS_TOOLTIP = "Save changes";
    private static final String UNDO_CHANGES_PROJECT_DETAILS_TOOLTIP = "Undo changes";
    private static final String COMPANY_PROJECT_LABEL = "Company Project";
    private static final String JOB_DESCRIPTION_LABEL = "Job Description";
    private static final String JOB_TYPE_LABEL = "Job Type";
    private static final String JOB_ADDRESS_LABEL = "Job Address";
    private static final String START_DATE_LABEL = "Start date";
    private static final String END_DATE_LABEL = "End date";

    private Project project;

    TextField companyProject = new TextField(COMPANY_PROJECT_LABEL);
    TextField jobDescription = new TextField(JOB_DESCRIPTION_LABEL);
    TextField jobType = new TextField(JOB_TYPE_LABEL);
    TextField jobAddress = new TextField(JOB_ADDRESS_LABEL);
    DatePicker startDate = new DatePicker(START_DATE_LABEL);
    DatePicker endDate = new DatePicker(END_DATE_LABEL);
    SvgIcon editIcon;
    SvgIcon cancelChangesIcon;
    SvgIcon saveChangesIcon;

    public ProjectDetailsFormLayout() {
        addClassName("project-details-form");
        configureFormFields(true);
        Span actionIconSection = buildActionIconsSection();

        add(actionIconSection, companyProject, jobDescription, jobType, jobAddress, startDate, endDate);

        setColspan(actionIconSection, 3);
        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
    }

    private void configureFormFields(boolean readonly) {
        companyProject.setReadOnly(readonly);
        jobType.setReadOnly(readonly);
        jobAddress.setReadOnly(readonly);
        jobDescription.setReadOnly(readonly);
        startDate.setReadOnly(readonly);
        endDate.setReadOnly(readonly);
    }

    private Span buildActionIconsSection() {
        Span iconActionSection = new Span();
        iconActionSection.addClassName("action-icons");

        editIcon = LineAwesomeIcon.EDIT.create();
        editIcon.setTooltipText(EDIT_PROJECT_DETAILS_TOOLTIP);
        editIcon.addClickListener(e -> configureActionIcons(true));

        saveChangesIcon = LineAwesomeIcon.SAVE.create();
        saveChangesIcon.setTooltipText(SAVE_CHANGES_PROJECT_DETAILS_TOOLTIP);
        saveChangesIcon.addClickListener(e -> persistChanges());
        saveChangesIcon.setVisible(false);

        cancelChangesIcon = LineAwesomeIcon.UNDO_SOLID.create();
        cancelChangesIcon.setTooltipText(UNDO_CHANGES_PROJECT_DETAILS_TOOLTIP);
        cancelChangesIcon.addClickListener(e -> discardChanges());
        cancelChangesIcon.setVisible(false);

        iconActionSection.add(editIcon, saveChangesIcon, cancelChangesIcon);
        return iconActionSection;
    }

    private void persistChanges() {
        if (thereArePendingChanges()) {
            updateProject();
            fireEvent(new SaveEvent(this, project));
        }
        configureActionIcons(false);
    }

    private void updateProject() {
        project.setCompanyProject(companyProject.getValue());
        project.setJobType(jobType.getValue());
        project.setJobAddress(jobAddress.getValue());
        project.setJobDescription(jobDescription.getValue());
        project.setStartDate(startDate.getValue());
        project.setEndDate(endDate.getValue());
    }

    private boolean thereArePendingChanges() {
        return (!Objects.equals(this.companyProject.getValue(), project.getCompanyProject()) ||
                !Objects.equals(this.jobDescription.getValue(), project.getJobDescription()) ||
                !Objects.equals(this.jobType.getValue(), project.getJobType()) ||
                !Objects.equals(this.jobAddress.getValue(), project.getJobAddress()) ||
                !Objects.equals(this.startDate.getValue(), project.getStartDate()) ||
                !Objects.equals(this.endDate.getValue(), project.getEndDate()));
    }

    private void discardChanges() {
        if (thereArePendingChanges()) {
            initFormFields();
        }
        configureActionIcons(false);
    }

    private void configureActionIcons(boolean isEditMode) {
        editIcon.setVisible(!isEditMode);
        saveChangesIcon.setVisible(isEditMode);
        cancelChangesIcon.setVisible(isEditMode);
        configureFormFields(!isEditMode);
    }

    public void setProject(Project project) {
        this.project = project;
        initFormFields();
    }

    private void initFormFields() {
        companyProject.setValue(project.getCompanyProject());
        jobType.setValue(project.getJobType());
        jobAddress.setValue(project.getJobAddress());
        jobDescription.setValue(project.getJobDescription());
        startDate.setValue(project.getStartDate());
        endDate.setValue(project.getEndDate());
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
}
