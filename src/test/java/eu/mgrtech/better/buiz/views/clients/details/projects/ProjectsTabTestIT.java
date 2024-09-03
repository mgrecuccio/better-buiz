package eu.mgrtech.better.buiz.views.clients.details.projects;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.LitRenderer;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.services.ProjectService;
import jakarta.annotation.Resource;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ProjectsTab.class, ProjectService.class})
class ProjectsTabTestIT {

    private final String clientId = "test-client-id";

    @Resource
    private ProjectService projectService;

    private ProjectsTab projectsTab;

    @BeforeEach
    public void setUp() {
        projectsTab = new ProjectsTab(projectService, clientId);
    }

    @Test
    public void testThatTheProjectGridIsStructuredAsExpected() {
        Grid<Project> projectGrid = projectsTab.projectGrid;

        List<Grid.Column<Project>> columns = projectGrid.getColumns();
        assertEquals(4, columns.size());

        Grid.Column<Project> companyProjectColumn = columns.get(0);
        assertEquals("Company Project", companyProjectColumn.getHeaderText());

        Grid.Column<Project> jobTitleColumn = columns.get(1);
        assertEquals("Job Title", jobTitleColumn.getHeaderText());

        Grid.Column<Project> openDetailsToggleColumn = columns.get(2);
        LitRenderer<Project> renderer = (LitRenderer<Project>) openDetailsToggleColumn.getRenderer();
        assertNotNull(renderer);
    }

    @Test
    public void testThatTheProjectGridContainsTheProjectsReturnedByTheService() {
        Grid<Project> projectGrid = projectsTab.projectGrid;

        List<Project> formProjects = projectGrid.getGenericDataView().getItems().toList();
        assertEquals(2, formProjects.size());

        List<Project> persistedProjects = projectService.getAllByClientId(clientId);
        assertEquals(formProjects, persistedProjects);
    }

    @Test
    public void testThatByDefaultAllProjectsAreNotVisible() {
        Grid<Project> projectGrid = projectsTab.projectGrid;

        List<Project> projects = projectGrid.getGenericDataView().getItems().toList();
        projects.forEach(project -> assertFalse(projectGrid.isDetailsVisible(project)));
    }

    @Test
    public void testThatTheAddProjectButtonIsPresent() {
        var toolbar = projectsTab.toolbar;
        assertTrue(toolbar.isVisible());

        var addProjectButton = projectsTab.addProjectButton;
        assertTrue(addProjectButton.isEnabled());
        assertTrue(addProjectButton.isVisible());

        var addProjectDialog = projectsTab.addProjectDialog;
        assertFalse(addProjectDialog.isOpened());
    }

    @Test
    public void testThatTheHintMessageIsHiddenWhenThereAreProjects() {
        Grid<Project> projectGrid = projectsTab.projectGrid;

        assertTrue(projectGrid.isVisible());
        int projectListSize = projectGrid.getGenericDataView().getItems().toList().size();
        assertNotEquals(0, projectListSize);

        var noProjectHint = projectsTab.noProjectHint;
        assertFalse(noProjectHint.isVisible());
    }

    @Test
    public void testThatTheHintMessageIsShownWhenProjectListIsEmpty() {
        projectService.deleteAll();

        projectsTab = new ProjectsTab(projectService, clientId);

        Grid<Project> projectGrid = projectsTab.projectGrid;

        assertFalse(projectGrid.isVisible());
        int projectListSize = projectGrid.getGenericDataView().getItems().toList().size();
        assertEquals(0, projectListSize);

        var noProjectHint = projectsTab.noProjectHint;
        assertTrue(noProjectHint.isVisible());
        assertEquals("No projects found.", noProjectHint.getText());
    }
}