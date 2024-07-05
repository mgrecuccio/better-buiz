package eu.mgrtech.better.buiz.views.clients.details.projects;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import eu.mgrtech.better.buiz.entities.Project;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteProjectDialogTest {

    @Test
    public void theDialogHasTheGoodStructureTest() {
        Project project = new Project(
                "company",
                "job-title",
                "job-decription",
                "job-type",
                "job-address",
                LocalDate.MIN,
                LocalDate.MAX
        );
        DeleteProjectDialog deleteProjectDialog = new DeleteProjectDialog(project);
        assertTrue(deleteProjectDialog.isCloseOnEsc());
    }
}