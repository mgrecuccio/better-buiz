package eu.mgrtech.better.buiz.services;

import eu.mgrtech.better.buiz.entities.Project;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private List<Project> projects = new ArrayList<>();

    public ProjectService() {
        Project project1 = new ProjectBuilder()
                .withId("id-1")
                .withCompanyProject("Mutual IT")
                .withJobType("Full Time")
                .withJobTitle("Technical Lead")
                .withJobAddress("Rue de la rue, 1000 Brussels")
                .withJobDescription("Integration of new health insurances")
                .withStartDate(LocalDate.now())
                .build();
        projects.add(project1);

        Project project2 = new ProjectBuilder()
                .withId("id-2")
                .withCompanyProject("Pluxee")
                .withJobType("Full Time")
                .withJobTitle("Senior Programmer")
                .withJobAddress("Rue de la reine, 1456 Leuven")
                .withJobDescription("Development of the Software for managing E-vouchers")
                .withStartDate(LocalDate.of(2021, 9, 1))
                .withEndDate(LocalDate.of(2024, 5, 30))
                .build();
        projects.add(project2);
    }

    public List<Project> getAllByClientId(String clientId) {
        return projects;
    }

    private class ProjectBuilder {
        private String id;
        private String companyProject;
        private String jobTitle;
        private String jobDescription;
        private String jobType;
        private String jobAddress;
        private LocalDate startDate;
        private LocalDate endDate;

        public ProjectBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ProjectBuilder withCompanyProject(String companyProject) {
            this.companyProject = companyProject;
            return this;
        }

        public ProjectBuilder withJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public ProjectBuilder withJobType(String jobType) {
            this.jobType = jobType;
            return this;
        }

        public ProjectBuilder withJobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
            return this;
        }

        public ProjectBuilder withJobAddress(String jobAddress) {
            this.jobAddress = jobAddress;
            return this;
        }

        public ProjectBuilder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public ProjectBuilder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Project build() {
            return new Project(
                    id,
                    companyProject,
                    jobTitle,
                    jobDescription,
                    jobType,
                    jobAddress,
                    startDate,
                    endDate
            );
        }
    }
}
