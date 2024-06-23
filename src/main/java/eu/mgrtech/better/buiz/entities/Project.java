package eu.mgrtech.better.buiz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
}
