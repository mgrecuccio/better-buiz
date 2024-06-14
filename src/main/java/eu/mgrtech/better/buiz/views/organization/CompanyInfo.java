package eu.mgrtech.better.buiz.views.organization;

public record CompanyInfo(
        String vatNumber,
        String companyName,
        String startDate,
        String address,
        String companyType,
        String companyStatus,
        String companyEmail,
        String companyGeneralManager
) {
}
