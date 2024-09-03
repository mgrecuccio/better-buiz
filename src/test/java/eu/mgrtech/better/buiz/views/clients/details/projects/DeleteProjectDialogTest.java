package eu.mgrtech.better.buiz.views.clients.details.projects;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import eu.mgrtech.better.buiz.entities.Project;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteProjectDialogTest {

    @Test
    public void testThatDialogIsStructuredAsExpected() {
        var project = new Project(
                "company",
                "job-title",
                "job-decription",
                "job-type",
                "job-address",
                LocalDate.MIN,
                LocalDate.MAX
        );
        var deleteProjectDialog = new DeleteProjectDialog(project);
        assertTrue(deleteProjectDialog.isCloseOnEsc());
    }
}