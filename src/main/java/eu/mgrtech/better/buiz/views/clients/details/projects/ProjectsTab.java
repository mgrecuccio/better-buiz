package eu.mgrtech.better.buiz.views.clients.details.projects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.events.project.AddProjectEvent;
import eu.mgrtech.better.buiz.events.project.DeleteProjectEvent;
import eu.mgrtech.better.buiz.events.project.UpdateProjectEvent;
import eu.mgrtech.better.buiz.services.ProjectService;

@Component
@Scope("prototype")
public class ProjectsTab extends Div  {

    private static final String COMPANY_PROJECT = "Company Project";
    private static final String JOB_TITLE = "Job Title";
    private static final String ADD_PROJECT_BTN_LABEL = "Add Project";
    private static final String NO_PROJECTS_FOUND_HINT_TEXT = "No projects found.";

    private final ProjectService projectService;
    private final String clientId;

    private ProjectDetailsFormLayout projectDetailsForm = new ProjectDetailsFormLayout();
    private ComponentRenderer<ProjectDetailsFormLayout, Project> personDetailsRenderer;

    Grid<Project> projectGrid = new Grid<>(Project.class, false);
    AddProjectDialog addProjectDialog = new AddProjectDialog();
    Button addProjectButton = new Button(ADD_PROJECT_BTN_LABEL);
    HorizontalLayout toolbar = new HorizontalLayout(addProjectButton);
    Div noProjectHint = new Div();

    public ProjectsTab(ProjectService projectService, String clientId) {
        this.projectService = projectService;
        this.clientId = clientId;

        addClassName("projects-tab");

        noProjectHint.setText(NO_PROJECTS_FOUND_HINT_TEXT);
        noProjectHint.addClassName("hint-no-projects");

        configureProjectsGrid();
        configureToolbar();

        projectDetailsForm.addUpdateListener(this::updateProject);
        addProjectDialog.addProjectListener(this::addNewProject);

        add(toolbar, projectGrid, noProjectHint);
    }

    private HorizontalLayout configureToolbar() {
        toolbar.addClassName("project-tab-toolbar");
        addProjectButton.addClickListener(click -> addProject(new Project()));
        return toolbar;
    }

    private void addProject(Project project) {
        addProjectDialog.setProject(project);
        addProjectDialog.open();
    }

    private void addNewProject(AddProjectEvent addProjectEvent) {
        projectService.add(addProjectEvent.getProject());
        updatedProjectList();
    }

    private void configureProjectsGrid() {
        projectGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        projectGrid.addColumn(Project::getCompany).setHeader(COMPANY_PROJECT);
        projectGrid.addColumn(Project::getJobTitle).setHeader(JOB_TITLE);
        projectGrid.addColumn(createToggleProejctDetailsRenderer());
        projectGrid.addComponentColumn(project -> configureDeleteProjectDialog(project));

        updatedProjectList();

        projectGrid.setDetailsVisibleOnClick(false);
        personDetailsRenderer = createPersonDetailsRenderer();
        projectGrid.setItemDetailsRenderer(personDetailsRenderer);
    }

    private Icon configureDeleteProjectDialog(Project project) {
        var deleteIcon = VaadinIcon.TRASH.create();
        deleteIcon.addClickListener(e -> openDeleteProjectDialog(project));
        return deleteIcon;
    }

    private void openDeleteProjectDialog(Project project) {
        var deleteProjectDialog = new DeleteProjectDialog(project);
        deleteProjectDialog.addDeleteListener(this::deleteProject);
        deleteProjectDialog.open();
    }

    private void deleteProject(DeleteProjectEvent deleteProjectEvent) {
        var project = deleteProjectEvent.getProject();
        if (project != null) {
            projectService.delete(project);
            updatedProjectList();
        }
    }

    private void updateProject(UpdateProjectEvent updateProjectEvent) {
        var updatedProject = updateProjectEvent.getProject();
        projectService.update(updatedProject);
        updatedProjectList();
        projectGrid.setDetailsVisible(updatedProject, true);
    }

    private void updatedProjectList() {
        var projectList = projectService.getAllByClientId(clientId);
        if (projectList.isEmpty()) {
            projectGrid.setVisible(false);
            noProjectHint.setVisible(true);
        } else {
            noProjectHint.setVisible(false);
            projectGrid.setVisible(true);
            projectGrid.setItems(projectService.getAllByClientId(clientId));
        }
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
