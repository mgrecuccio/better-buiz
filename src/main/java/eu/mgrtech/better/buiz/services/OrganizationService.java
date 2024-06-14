package eu.mgrtech.better.buiz.services;

import eu.mgrtech.better.buiz.views.organization.CompanyInfo;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    public CompanyInfo getCompanyInfoByVatNumber(String vatNumber) {
        return new CompanyInfo(
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
