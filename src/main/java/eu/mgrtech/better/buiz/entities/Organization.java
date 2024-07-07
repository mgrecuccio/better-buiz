package eu.mgrtech.better.buiz.entities;

public record Organization (
        String id,
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
