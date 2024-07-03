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
@AllArgsConstructor
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
}
