package eu.mgrtech.better.buiz.services;

import eu.mgrtech.better.buiz.views.clients.Client;
import eu.mgrtech.better.buiz.views.clients.ClientStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private List<Client> clients = new ArrayList<>();

    public ClientService() {
        this.clients.add(new Client(
                "BE1123455",
                "Mutual It",
                ClientStatus.ACTIVE,
                "Marc Doe",
                "marc.doe@email.com"));

        this.clients.add(new Client(
                "BE9923466",
                "AXA",
                ClientStatus.ACTIVE,
                "Jimmy Foh",
                "jimmy.foh@email.com"));

        this.clients.add(new Client(
                "BE6545640",
                "BNP Paribas",
                ClientStatus.DRAFT,
                "Elise Loy",
                "elise.loy@email.com"));

        this.clients.add(new Client(
                "BE3412631",
                "Meddition",
                ClientStatus.INACTIVE,
                "Loris Backer",
                "loris.backer@email.com"));

        this.clients.add(new Client(
                "BE4521631",
                "Arhs",
                ClientStatus.DECLINED,
                "Els Moerman",
                "els.moherman@email.com")
        );
    }

    public List<Client> getClientInfoByCompanyVatNumber(String filter) {
        if (StringUtils.isBlank(filter)) {
            return clients;
        }
        return findFiltered(clients, filter);
    }

    private List<Client> findFiltered(List<Client> clientInfos, String filter) {
        return clientInfos
                .stream()
                .filter(clientInfo ->
                        clientInfo.getVatNumber().toLowerCase().contains(filter.toLowerCase())
                                || clientInfo.getName().toLowerCase().contains(filter.toLowerCase())
                                || clientInfo.getIntermediary().toLowerCase().contains(filter.toLowerCase())
                )
                .toList();
    }

    public void saveClient(Client client) {
        this.clients.add(client);
    }
}
