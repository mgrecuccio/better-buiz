package eu.mgrtech.better.buiz.events.project;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.views.clients.details.projects.AddProjectDialog;

public final class AddProjectEvent extends AddProjectDialogEvent {

    public AddProjectEvent(AddProjectDialog source, Project project) {
        super(source, project);
    }
}
