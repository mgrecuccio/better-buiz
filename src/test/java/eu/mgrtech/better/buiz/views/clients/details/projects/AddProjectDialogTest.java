package eu.mgrtech.better.buiz.views.clients.details.projects;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import eu.mgrtech.better.buiz.entities.Project;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddProjectDialogTest {

    private AddProjectDialog addProjectDialog = new AddProjectDialog();

    @Test
    public void testThatTheDialogIsCorrectlyInitialized() {
        addProjectDialog.setProject(new Project());

        // Dialog fields
        assertTrue(addProjectDialog.company.isVisible());
        assertTrue(addProjectDialog.company.isEnabled());
        assertTrue(addProjectDialog.company.isEmpty());

        assertTrue(addProjectDialog.jobTitle.isVisible());
        assertTrue(addProjectDialog.jobTitle.isEnabled());
        assertTrue(addProjectDialog.jobTitle.isEmpty());

        assertTrue(addProjectDialog.jobDescription.isVisible());
        assertTrue(addProjectDialog.jobDescription.isEnabled());
        assertTrue(addProjectDialog.jobDescription.isEmpty());

        assertTrue(addProjectDialog.jobType.isVisible());
        assertTrue(addProjectDialog.jobType.isEnabled());
        assertTrue(addProjectDialog.jobType.isEmpty());

        assertTrue(addProjectDialog.jobAddress.isVisible());
        assertTrue(addProjectDialog.jobAddress.isEnabled());
        assertTrue(addProjectDialog.jobAddress.isEmpty());

        assertTrue(addProjectDialog.startDate.isVisible());
        assertTrue(addProjectDialog.startDate.isEnabled());
        assertTrue(addProjectDialog.startDate.isEmpty());

        assertTrue(addProjectDialog.endDate.isVisible());
        assertTrue(addProjectDialog.endDate.isEnabled());
        assertTrue(addProjectDialog.endDate.isEmpty());
    }

    @Test
    public void testThatTheProjectIsNotPersistedIfTheInputIsInvalid() {
        var project = new Project();

        addProjectDialog.setProject(project);

        addProjectDialog.company.setValue("Test company");
        addProjectDialog.jobDescription.setValue("Test description");
        addProjectDialog.jobTitle.setValue("Test company");
        addProjectDialog.jobType.setValue(""); //invalid field
        addProjectDialog.startDate.setValue(LocalDate.MIN);

        var savedProjectRef = new AtomicReference<>(null);

        addProjectDialog.addProjectListener(e -> savedProjectRef.set(e.getProject()));
        addProjectDialog.saveButton.click();

        var savedProject = savedProjectRef.get();
        assertNull(savedProject);
    }

    @Test
    public void testThatTheProjectIsPersistedIfTheInputIsValid() {
        var project = new Project();

        addProjectDialog.setProject(project);

        addProjectDialog.company.setValue("Test company");
        addProjectDialog.jobDescription.setValue("Test description");
        addProjectDialog.jobTitle.setValue("Test job title");
        addProjectDialog.jobType.setValue("Full Time");
        addProjectDialog.jobAddress.setValue("Test address");
        addProjectDialog.startDate.setValue(LocalDate.MIN);
        addProjectDialog.endDate.setValue(LocalDate.MAX);

        AtomicReference<Project> savedProjectRef = new AtomicReference<>(null);

        addProjectDialog.addProjectListener(e -> savedProjectRef.set(e.getProject()));
        addProjectDialog.saveButton.click();

        Project savedProject = savedProjectRef.get();
        assertNotNull(savedProject);

        assertEquals("Test company", savedProject.getCompany());
        assertEquals("Test description", savedProject.getJobDescription());
        assertEquals("Test job title", savedProject.getJobTitle());
        assertEquals("Full Time", savedProject.getJobType());
        assertEquals("Test address", savedProject.getJobAddress());
        assertEquals(LocalDate.MIN, savedProject.getStartDate());
        assertEquals(LocalDate.MAX, savedProject.getEndDate());
    }

    @Test
    public void testThatTheButtonsSaveAndCancelArePresent() {
        var saveButton = addProjectDialog.saveButton;

        assertTrue(saveButton.isVisible());
        assertTrue(saveButton.isEnabled());

        var cancelButton = addProjectDialog.cancelButton;
        assertTrue(cancelButton.isVisible());
        assertTrue(cancelButton.isEnabled());
    }
}