package eu.mgrtech.better.buiz.services;

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
                "Technical Lead",
                "Leading a team for developing a new product",
                "Full Time",
                "Rue de la rue, 1000 Brussels",
                LocalDate.now(),
                null
        );
        projects.add(project1);

        Project project2 = new Project(
                "id-2",
                "Pluxee",
                "Senior Programmer",
                "Revamping the old application by introducing a new microservices architecture",
                "Full Time",
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
    }

    public void add(Project project) {
        this.projects.add(project);
    }
}
