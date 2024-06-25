package eu.mgrtech.better.buiz.entities;

import eu.mgrtech.better.buiz.views.clients.ClientStatus;
import eu.mgrtech.better.buiz.validator.VATNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(vatNumber, client.vatNumber) && Objects.equals(name, client.name) && status == client.status
               && Objects.equals(intermediary, client.intermediary) && Objects.equals(contactEmailAddress, client.contactEmailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vatNumber, name, status, intermediary, contactEmailAddress);
    }
}
