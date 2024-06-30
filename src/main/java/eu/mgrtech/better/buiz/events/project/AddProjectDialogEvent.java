package eu.mgrtech.better.buiz.events.project;

import com.vaadin.flow.component.ComponentEvent;
import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.views.clients.details.projects.AddProjectDialog;
import lombok.Getter;

@Getter
public abstract sealed class AddProjectDialogEvent extends ComponentEvent<AddProjectDialog> permits AddProjectEvent {

    private Project project;

    public AddProjectDialogEvent(AddProjectDialog source, Project project) {
        super(source, false);
        this.project = project;
    }
}
