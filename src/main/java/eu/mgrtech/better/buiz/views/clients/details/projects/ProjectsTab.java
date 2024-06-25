package eu.mgrtech.better.buiz.views.clients.details.projects;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.events.project.SaveEvent;
import eu.mgrtech.better.buiz.services.ProjectService;

public class ProjectsTab extends Div  {

    private static final String COMPANY_PROJECT = "Company Project";
    private static final String JOB_TITLE = "Job Title";

    private final ProjectService projectService;
    private final String clientId;

    private ProjectDetailsFormLayout projectDetailsForm = new ProjectDetailsFormLayout();
    private ComponentRenderer<ProjectDetailsFormLayout, Project> personDetailsRenderer;

    Grid<Project> projectGrid = new Grid<>(Project.class, false);

    public ProjectsTab(ProjectService projectService, String clientId) {
        addClassName("projects-view");

        this.projectService = projectService;
        this.clientId = clientId;

        configureProjectsGrid();
        projectDetailsForm.addSaveListener(this::updateProject);

        add(projectGrid);
    }

    private void configureProjectsGrid() {
        projectGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        projectGrid.addColumn(Project::getCompanyProject).setHeader(COMPANY_PROJECT);
        projectGrid.addColumn(Project::getJobTitle).setHeader(JOB_TITLE);
        projectGrid.addColumn(createToggleProejctDetailsRenderer());

        updatedProjectList();

        projectGrid.setDetailsVisibleOnClick(false);
        personDetailsRenderer = createPersonDetailsRenderer();
        projectGrid.setItemDetailsRenderer(personDetailsRenderer);
    }

    private void updateProject(SaveEvent saveEvent) {
        Project updatedProject = saveEvent.getProject();
        projectService.update(updatedProject);
        updatedProjectList();
        projectGrid.setDetailsVisible(updatedProject, true);
    }

    private void updatedProjectList() {
        projectGrid.setItems(projectService.getAllByClientId(clientId));
    }

    private Renderer<Project> createToggleProejctDetailsRenderer() {
        return LitRenderer.<Project>of("<vaadin-button theme=\"tertiary\" @click=\"${handleClick}\">Open details</vaadin-button>")
                          .withFunction("handleClick", project -> {
                              projectGrid.setDetailsVisible(project, !projectGrid.isDetailsVisible(project));
                          });
    }

    private ComponentRenderer<ProjectDetailsFormLayout, Project> createPersonDetailsRenderer() {
        return new ComponentRenderer<>(() -> projectDetailsForm, ProjectDetailsFormLayout::setProject);
    }
}
