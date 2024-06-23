package eu.mgrtech.better.buiz.views.clients.details.projects;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.services.ProjectService;

public class ProjectsTab extends Div {

    private static final String COMPANY_PROJECT = "Company Project";
    private static final String JOB_TITLE = "Job Title";

    private final ProjectService projectService;
    private final String clientId;

    Grid<Project> projectGrid = new Grid<>(Project.class, false);

    public ProjectsTab(ProjectService projectService, String clientId) {
        addClassName("projects-view");

        this.clientId = clientId;
        this.projectService = projectService;

        configureProjectsGrid();
        add(projectGrid);
    }

    private void configureProjectsGrid() {
        projectGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        projectGrid.addColumn(Project::getCompanyProject).setHeader(COMPANY_PROJECT);
        projectGrid.addColumn(Project::getJobTitle).setHeader(JOB_TITLE);
        projectGrid.addColumn(createToggleProejctDetailsRenderer());

        projectGrid.setItems(projectService.getAllByClientId(clientId));

        projectGrid.setDetailsVisibleOnClick(false);
        projectGrid.setItemDetailsRenderer(createPersonDetailsRenderer());
    }

    private Renderer<Project> createToggleProejctDetailsRenderer() {
        return LitRenderer.<Project>of(
                        "<vaadin-button theme=\"tertiary\" @click=\"${handleClick}\">Open details</vaadin-button>")
                .withFunction("handleClick",
                        project -> {
                            projectGrid.setDetailsVisible(project, !projectGrid.isDetailsVisible(project));
                            //projectDetailsForm.cancelEditEvent();
                        });
    }

    private ComponentRenderer<ProjectDetailsFormLayout, Project> createPersonDetailsRenderer() {
        return new ComponentRenderer<>(ProjectDetailsFormLayout::new, ProjectDetailsFormLayout::setProject);
    }
}
