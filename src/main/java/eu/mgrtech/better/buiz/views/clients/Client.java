package eu.mgrtech.better.buiz.views.clients;

import eu.mgrtech.better.buiz.views.clients.validator.VATNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

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
