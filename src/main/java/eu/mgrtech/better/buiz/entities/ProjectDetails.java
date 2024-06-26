package eu.mgrtech.better.buiz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetails {

    private String description;
    private String type;
    private String address;
    private LocalDate startDate;
    private LocalDate endDate;
}
