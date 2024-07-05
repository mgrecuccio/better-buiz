package eu.mgrtech.better.buiz.views.clients.details.projects;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.shared.Registration;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.events.project.DeleteProjectEvent;

public class DeleteProjectDialog extends ConfirmDialog {

    private static final String DIALOG_TITLE = "Delete Project";
    private static final String CONFIRM_PROJECT_DELETION_LABEL = "Are you sure you want to permanently delete this project?";
    private static final String DELETE_PROJECT_BTN_LABEL = "Delete";

    private Project project;

    public DeleteProjectDialog(Project project) {
        addClassName("delete-project-dialog");
        this.project = project;

        setHeader(DIALOG_TITLE);
        setText(CONFIRM_PROJECT_DELETION_LABEL);

        setCancelable(true);
        addCancelListener(event -> close());

        setConfirmText(DELETE_PROJECT_BTN_LABEL);
        setConfirmButtonTheme("error primary");
        addConfirmListener(event -> persistDeletion());
    }

    private void persistDeletion() {
        fireEvent(new DeleteProjectEvent(this, project));
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteProjectEvent> listener) {
        return addListener(DeleteProjectEvent.class, listener);
    }
}
