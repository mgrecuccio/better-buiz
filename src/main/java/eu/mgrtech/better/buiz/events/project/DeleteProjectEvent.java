package eu.mgrtech.better.buiz.events.project;

import com.vaadin.flow.component.Component;

import eu.mgrtech.better.buiz.entities.Project;

public final class DeleteProjectEvent extends ProjectEvent {

    public DeleteProjectEvent(Component source, Project project) {
        super(source, project);
    }
}
