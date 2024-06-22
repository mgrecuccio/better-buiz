package eu.mgrtech.better.buiz.services;

import eu.mgrtech.better.buiz.entities.Project;
import eu.mgrtech.better.buiz.entities.ProjectDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private List<Project> projects = new ArrayList<>();

    public ProjectService() {
        ProjectDetails projectDetails1 = new ProjectDetails(
                "Integration of new health insurances",
                "Full Time",
                "Rue de la rue, 1000 Brussels",
                LocalDate.now(),
                null
        );
        Project project1 = new Project(
                "12345",
                "Mutual IT",
                "Technical Lead",
                projectDetails1
        );
        projects.add(project1);

        ProjectDetails projectDetails2 = new ProjectDetails(
                "Development of the Software for managing E-vouchers",
                "Full Time",
                "Rue de la reine, 1456 Leuven",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2024, 5, 30)
        );
        Project project2 = new Project(
                "12346",
                "Pluxee",
                "Senior Programmer",
                projectDetails2
        );
        projects.add(project2);
    }

    public List<Project> getAllByClientId(String clientId) {
        return projects;
    }
}
