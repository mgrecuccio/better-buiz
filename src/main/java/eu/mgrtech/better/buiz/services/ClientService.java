package eu.mgrtech.better.buiz.services;

import eu.mgrtech.better.buiz.views.clients.ClientInfo;
import eu.mgrtech.better.buiz.views.clients.ClientStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private List<ClientInfo> clientInfos = List.of(
            new ClientInfo(
                    "BE1123455",
                    "Mutual It",
                    ClientStatus.ACTIVE,
                    "John Doe",
                    "john.doe@email.com"),
            new ClientInfo(
                    "BE9923466",
                    "AXA",
                    ClientStatus.ACTIVE,
                    "Jimmy Foh",
                    "jimmy.foh@email.com"),
            new ClientInfo(
                    "BE6545640",
                    "BNP Paribas",
                    ClientStatus.DRAFT,
                    "Elise Loy",
                    "elise.loy@email.com"),
            new ClientInfo(
                    "BE3412631",
                    "Meddition",
                    ClientStatus.INACTIVE,
                    "Loris Backer",
                    "loris.backer@email.com"),
            new ClientInfo(
                    "BE4521631",
                    "Arhs",
                    ClientStatus.REFUSED,
                    "Els Moerman",
                    "els.moherman@email.com")
    );

    public List<ClientInfo> getClientInfoByCompanyVatNumber(String filter) {
        if(StringUtils.isBlank(filter)) {
            return clientInfos;
        }
        return findFiltered(clientInfos, filter);
    }

    private List<ClientInfo> findFiltered(List<ClientInfo> clientInfos, String filter) {
        return clientInfos
                .stream()
                .filter(clientInfo ->
                        clientInfo.getVatNumber().toLowerCase().contains(filter.toLowerCase())
                                || clientInfo.getName().toLowerCase().contains(filter.toLowerCase())
                                || clientInfo.getIntermediary().toLowerCase().contains(filter.toLowerCase())
                )
                .toList();
    }
}
