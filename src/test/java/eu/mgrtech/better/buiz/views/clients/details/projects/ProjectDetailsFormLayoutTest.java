package eu.mgrtech.better.buiz.views.clients.details.projects;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.icon.SvgIcon;

import eu.mgrtech.better.buiz.entities.Project;
import static com.helger.commons.mock.CommonsAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectDetailsFormLayoutTest {

    private ProjectDetailsFormLayout projectDetailsForm = new ProjectDetailsFormLayout();
    private Project project;

    @BeforeEach
    public void setUp() {
        project = new Project(
                "id-1",
                "Mutual IT",
                "Full Time",
                "Technical Lead",
                "Rue de la rue, 1000 Brussels",
                "Integration of new health insurances",
                LocalDate.now(),
                null
        );
        projectDetailsForm.setProject(project);
    }

    @Test
    public void theFormFieldsAreFilledTest() {
        assertEquals(project.getCompanyProject(), projectDetailsForm.companyProject.getValue());
        assertEquals(project.getJobType(), projectDetailsForm.jobType.getValue());
        assertEquals(project.getJobAddress(), projectDetailsForm.jobAddress.getValue());
        assertEquals(project.getJobDescription(), projectDetailsForm.jobDescription.getValue());
        assertEquals(project.getStartDate(), projectDetailsForm.startDate.getValue());
        assertEquals(project.getEndDate(), projectDetailsForm.endDate.getValue());
    }

    @Test
    public void theFormFieldsAreReadOnlyTest() {
        assertTrue(projectDetailsForm.companyProject.isReadOnly());
        assertTrue(projectDetailsForm.jobType.isReadOnly());
        assertTrue(projectDetailsForm.jobAddress.isReadOnly());
        assertTrue(projectDetailsForm.jobDescription.isReadOnly());
        assertTrue(projectDetailsForm.startDate.isReadOnly());
        assertTrue(projectDetailsForm.endDate.isReadOnly());
    }

    @Test
    public void theActionIconsAreWellConfiguredTest() {
        SvgIcon editIcon = projectDetailsForm.editIcon;
        SvgIcon saveChangesIcon = projectDetailsForm.saveChangesIcon;
        SvgIcon cancelChangesIcon = projectDetailsForm.cancelChangesIcon;

        assertTrue(editIcon.isVisible());
        assertFalse(saveChangesIcon.isVisible());
        assertFalse(cancelChangesIcon.isVisible());
    }

    @Test
    public void whenIClickOnTheEditIconTheFormIsInEditModeTest() {
        SvgIcon editIcon = projectDetailsForm.editIcon;
        SvgIcon saveChangesIcon = projectDetailsForm.saveChangesIcon;
        SvgIcon cancelChangesIcon = projectDetailsForm.cancelChangesIcon;

        assertTrue(editIcon.isVisible());
        assertFalse(saveChangesIcon.isVisible());
        assertFalse(cancelChangesIcon.isVisible());

        assertTrue(projectDetailsForm.companyProject.isReadOnly());
        assertTrue(projectDetailsForm.jobType.isReadOnly());
        assertTrue(projectDetailsForm.jobAddress.isReadOnly());
        assertTrue(projectDetailsForm.jobDescription.isReadOnly());
        assertTrue(projectDetailsForm.startDate.isReadOnly());
        assertTrue(projectDetailsForm.endDate.isReadOnly());

        ComponentUtil.fireEvent(editIcon, new ClickEvent<>(editIcon));

        assertFalse(editIcon.isVisible());
        assertTrue(saveChangesIcon.isVisible());
        assertTrue(cancelChangesIcon.isVisible());

        assertFalse(projectDetailsForm.companyProject.isReadOnly());
        assertFalse(projectDetailsForm.jobType.isReadOnly());
        assertFalse(projectDetailsForm.jobAddress.isReadOnly());
        assertFalse(projectDetailsForm.jobDescription.isReadOnly());
        assertFalse(projectDetailsForm.startDate.isReadOnly());
        assertFalse(projectDetailsForm.endDate.isReadOnly());
    }

    @Test
    public void whenClickingOnSaveTheChangesArePersistedTest() {
        SvgIcon editIcon = projectDetailsForm.editIcon;
        SvgIcon saveChangesIcon = projectDetailsForm.saveChangesIcon;

        ComponentUtil.fireEvent(editIcon, new ClickEvent<>(editIcon));

        projectDetailsForm.jobDescription.setValue("A new description test");
        ComponentUtil.fireEvent(saveChangesIcon, new ClickEvent<>(saveChangesIcon));

        assertEquals("A new description test", project.getJobDescription());
    }

    @Test
    public void whenClickingOnCancelTheChangesAreRevertedTest() {
        SvgIcon editIcon = projectDetailsForm.editIcon;
        SvgIcon cancelChangesIcon = projectDetailsForm.cancelChangesIcon;

        ComponentUtil.fireEvent(editIcon, new ClickEvent<>(editIcon));

        projectDetailsForm.jobDescription.setValue("A new description test");
        ComponentUtil.fireEvent(cancelChangesIcon, new ClickEvent<>(cancelChangesIcon));

        assertNotEquals("A new description test", project.getJobDescription());
    }
}