package eu.mgrtech.better.buiz.events.project;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

import eu.mgrtech.better.buiz.entities.Project;
import lombok.Getter;

@Getter
public abstract sealed class ProjectEvent extends ComponentEvent<Component> permits AddProjectEvent, DeleteProjectEvent, UpdateProjectEvent {

    private Project project;

    public ProjectEvent(Component source, Project project) {
        super(source, false);
        this.project = project;
    }
}
