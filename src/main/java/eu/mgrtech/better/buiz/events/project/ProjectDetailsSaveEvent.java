package eu.mgrtech.better.buiz.events.project;

import com.vaadin.flow.component.ComponentEvent;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.views.clients.details.projects.ProjectDetailsFormLayout;
import lombok.Getter;

@Getter
public abstract sealed class ProjectDetailsSaveEvent extends ComponentEvent<ProjectDetailsFormLayout> permits SaveEvent {

    private Project project;

    public ProjectDetailsSaveEvent(ProjectDetailsFormLayout source, Project project) {
        super(source, false);
        this.project = project;
    }
}
