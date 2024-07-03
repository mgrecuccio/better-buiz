package eu.mgrtech.better.buiz.entities;

import eu.mgrtech.better.buiz.validators.VATNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Client {

    private UUID id;

    @VATNumber
    @NotEmpty
    private String vatNumber;

    @NotEmpty
    private String name;

    private ClientStatus status = ClientStatus.DRAFT;

    @NotEmpty
    private String intermediary;

    @Email
    @NotEmpty
    private String contactEmailAddress;
}
