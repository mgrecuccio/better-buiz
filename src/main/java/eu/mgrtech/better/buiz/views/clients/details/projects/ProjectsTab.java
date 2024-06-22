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

    private final ProjectService projectService;
    private final String clientId;

    Grid<Project> grid = new Grid<>(Project.class, false);
    ProjectDetailsFormLayout projectDetailsForm = new ProjectDetailsFormLayout();

    public ProjectsTab(String clientId) {
        addClassName("projects-tab");

        this.clientId = clientId;
        this.projectService = new ProjectService();

        grid.addColumn(Project::getClientName).setHeader("Client");
        grid.addColumn(Project::getRole).setHeader("Profession");
        grid.addColumn(createToggleDetailsRenderer(grid));

        grid.setDetailsVisibleOnClick(false);
        grid.setItemDetailsRenderer(createPersonDetailsRenderer());

        grid.setItems(projectService.getAllByClientId(clientId));

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        add(grid);
    }

    private Renderer<Project> createToggleDetailsRenderer(Grid<Project> grid) {
        return LitRenderer.<Project>of(
                        "<vaadin-button theme=\"tertiary\" @click=\"${handleClick}\">Open details</vaadin-button>")
                .withFunction("handleClick",
                        project -> {
                            grid.setDetailsVisible(project, !grid.isDetailsVisible(project));
                            projectDetailsForm.cancelEditEvent();
                        });
    }

    private ComponentRenderer<ProjectDetailsFormLayout, Project> createPersonDetailsRenderer() {
        return new ComponentRenderer<>(() -> projectDetailsForm, ProjectDetailsFormLayout::setProject);
    }
}
