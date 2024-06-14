package eu.mgrtech.better.buiz.views.clients;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientInfo {

    private String vatNumber;
    private String name;
    private ClientStatus status;
    private String intermediary;
    private String contactEmailAddress;
}
