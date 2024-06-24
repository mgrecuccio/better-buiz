package eu.mgrtech.better.buiz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private String id;
    private String companyProject;
    private String jobTitle;
    private String jobDescription;
    private String jobType;
    private String jobAddress;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(companyProject, project.companyProject) && Objects.equals(jobTitle, project.jobTitle)
               && Objects.equals(jobDescription, project.jobDescription) && Objects.equals(jobType, project.jobType) && Objects.equals(jobAddress,
                                                                                                                                       project.jobAddress
        ) && Objects.equals(startDate, project.startDate) && Objects.equals(endDate, project.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyProject, jobTitle, jobDescription, jobType, jobAddress, startDate, endDate);
    }
}
