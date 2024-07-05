package eu.mgrtech.better.buiz.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Project {

    private String id = UUID.randomUUID().toString();

    @NotEmpty(message = "The company field is mandatory")
    private String company;

    @NotEmpty
    private String jobTitle;

    private String jobDescription;

    @NotEmpty
    private String jobType;

    private String jobAddress;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    public Project(
            String company,
            String jobTitle,
            String jobDescription,
            String jobType,
            String jobAddress,
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.company = company;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobType = jobType;
        this.jobAddress = jobAddress;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
