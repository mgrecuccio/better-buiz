package eu.mgrtech.better.buiz.entities;

import eu.mgrtech.better.buiz.views.clients.ClientStatus;
import eu.mgrtech.better.buiz.validator.VATNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
