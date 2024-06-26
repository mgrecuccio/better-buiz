package eu.mgrtech.better.buiz.services;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.entities.ProjectDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import eu.mgrtech.better.buiz.entities.Project;

@Service
public class ProjectService {

    private List<Project> projects = new ArrayList<>();

    public ProjectService() {
        Project project1 = new Project(
                "id-1",
                "Mutual IT",
                "Full Time",
                "Technical Lead",
                "Rue de la rue, 1000 Brussels",
                "Integration of new health insurances",
                LocalDate.now(),
                null
        );
        projects.add(project1);

        Project project2 = new Project(
                "id-2",
                "Pluxee",
                "Full Time",
                "Technical Lead",
                "Senior Programmer",
                "Rue de la reine, 1456 Leuven",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2024, 5, 30)
        );
        projects.add(project2);
    }

    public List<Project> getAllByClientId(String clientId) {
        return projects;
    }

    public void update(Project project) {
        System.out.println("Project with id " + project.getId() + " updated");
    }
}
