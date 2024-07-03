package eu.mgrtech.better.buiz.services;

import org.springframework.stereotype.Service;

import eu.mgrtech.better.buiz.entities.Organization;

@Service
public class OrganizationService {

    public Organization getOrganizationInfoByVatNumber(String vatNumber) {
        return new Organization(
                vatNumber,
                "MGRTECH",
                "19-01-2024",
                "Rue Servandoni, 45 - 1130 Haren",
                "Limited liability company.",
                "ACTIVE",
                "test@gmail.com",
                "Marco Grecuccio"
        );
    }
}
