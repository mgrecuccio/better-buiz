package eu.mgrtech.better.buiz.events.project;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.views.clients.details.projects.ProjectDetailsFormLayout;

public final class SaveEvent extends ProjectDetailsSaveEvent {

    public SaveEvent(ProjectDetailsFormLayout source, Project project) {
        super(source, project);
    }
}
