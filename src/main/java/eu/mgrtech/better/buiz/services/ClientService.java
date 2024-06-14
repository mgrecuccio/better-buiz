package eu.mgrtech.better.buiz.services;

import eu.mgrtech.better.buiz.views.clients.ClientInfo;
import eu.mgrtech.better.buiz.views.clients.ClientStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    public List<ClientInfo> getClientInfoByCompanyVatNumber(String vatNumber) {
        var clientInfos = new ArrayList<ClientInfo>();
        var clientInfo1 = new ClientInfo(
                "BE1123455",
                "Mutual It",
                ClientStatus.ACTIVE,
                "John Doe",
                "john.doe@email.com");
        clientInfos.add(clientInfo1);

        var clientInfo2 = new ClientInfo(
                "BE9923466",
                "AXA",
                ClientStatus.ACTIVE,
                "Jimmy Foh",
                "jimmy.foh@email.com");
        clientInfos.add(clientInfo2);

        var clientInfo3 = new ClientInfo(
                "BE6545640",
                "BNP Paribas",
                ClientStatus.DRAFT,
                "Elise Loy",
                "elise.loy@email.com");
        clientInfos.add(clientInfo3);

        var clientInfo4 = new ClientInfo(
                "BE3412631",
                "Meddition",
                ClientStatus.INACTIVE,
                "Loris Backer",
                "loris.backer@email.com");
        clientInfos.add(clientInfo4);

        return clientInfos;
    }
}
