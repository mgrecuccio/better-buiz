package eu.mgrtech.better.buiz.events.project;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.views.clients.details.projects.ProjectDetailsFormLayout;

public final class UpdateProjectEvent extends ProjectEvent {

    public UpdateProjectEvent(ProjectDetailsFormLayout source, Project project) {
        super(source, project);
    }
}
