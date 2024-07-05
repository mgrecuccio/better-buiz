package eu.mgrtech.better.buiz.views.clients.details.projects;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import eu.mgrtech.better.buiz.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.renderer.LitRenderer;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.services.ProjectService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectsTabTest {

    private final String clientId = "test-client-id";
    private ProjectsTab projectsTab;
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        projectService = new ProjectService();
        projectsTab = new ProjectsTab(projectService, clientId);
    }

    @Test
    public void theProjectGridHasTheGoodStructureTest() {
        Grid<Project> projectGrid = projectsTab.projectGrid;

        List<Grid.Column<Project>> columns = projectGrid.getColumns();
        assertEquals(4, columns.size());

        Grid.Column<Project> companyProjectColumn = columns.get(0);
        assertEquals("Company Project", companyProjectColumn.getHeaderText());

        Grid.Column<Project> jobTitleColumn = columns.get(1);
        assertEquals("Job Title", jobTitleColumn.getHeaderText());

        Grid.Column<Project> openDetailsToggleColumn = columns.get(2);
        LitRenderer<Project> renderer = (LitRenderer<Project>) openDetailsToggleColumn.getRenderer();
        assertTrue(renderer instanceof LitRenderer<Project>);

        projectService.getAllByClientId(clientId).forEach(project -> assertFalse(projectGrid.isDetailsVisible(project)));
    }

    @Test
    public void theProjectGridIsFilledTest() {
        Grid<Project> projectGrid = projectsTab.projectGrid;

        List<Project> formProjects = projectGrid.getGenericDataView().getItems().toList();
        assertEquals(2, formProjects.size());

        List<Project> persistedProjects = projectService.getAllByClientId(clientId);
        assertEquals(formProjects, persistedProjects);
    }

    @Test
    public void theProjectButtonIsPresentTest() {
        HorizontalLayout toolbar = projectsTab.toolbar;
        assertTrue(toolbar.isVisible());

        Button addProjectButton = projectsTab.addProjectButton;
        assertTrue(addProjectButton.isEnabled());
        assertTrue(addProjectButton.isVisible());

        AddProjectDialog addProjectDialog = projectsTab.addProjectDialog;
        assertFalse(addProjectDialog.isOpened());
    }

    @Test
    public void theNoProjectsHintIsHiddenWhenThereAreProjectsTest() {
        Grid<Project> projectGrid = projectsTab.projectGrid;

        assertTrue(projectGrid.isVisible());
        int projectListSize = projectGrid.getGenericDataView().getItems().toList().size();
        assertNotEquals(0, projectListSize);

        Div noProjectHint = projectsTab.noProjectHint;
        assertFalse(noProjectHint.isVisible());
    }

    @Test
    public void theNoProjectsHintIsShownWhenThereAreNoProjectsTest() {
        projectService.deleteAll();

        projectsTab = new ProjectsTab(projectService, clientId);

        Grid<Project> projectGrid = projectsTab.projectGrid;

        assertFalse(projectGrid.isVisible());
        int projectListSize = projectGrid.getGenericDataView().getItems().toList().size();
        assertEquals(0, projectListSize);

        Div noProjectHint = projectsTab.noProjectHint;
        assertTrue(noProjectHint.isVisible());
        assertEquals("No projects found.", noProjectHint.getText());
    }
}