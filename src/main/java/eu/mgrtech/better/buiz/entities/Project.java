package eu.mgrtech.better.buiz.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private String id = UUID.randomUUID().toString();

    @NotNull
    private String company;

    @NotNull
    private String jobTitle;

    private String jobDescription;

    @NotNull
    private String jobType;

    private String jobAddress;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(company, project.company) && Objects.equals(jobTitle, project.jobTitle)
               && Objects.equals(jobDescription, project.jobDescription) && Objects.equals(jobType, project.jobType) &&
               Objects.equals(jobAddress, project.jobAddress) && Objects.equals(startDate, project.startDate) && Objects.equals(endDate, project.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, jobTitle, jobDescription, jobType, jobAddress, startDate, endDate);
    }
}
