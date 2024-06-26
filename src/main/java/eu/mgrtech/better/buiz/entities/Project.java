package eu.mgrtech.better.buiz.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @NotNull
    private String id;
    @NotNull
    private String clientName;
    @NotNull
    private String role;
    @NotNull
    private ProjectDetails projectDetails;
}
