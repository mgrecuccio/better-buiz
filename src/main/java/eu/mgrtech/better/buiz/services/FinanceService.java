package eu.mgrtech.better.buiz.services;

import java.util.List;

import org.springframework.stereotype.Service;

import eu.mgrtech.better.buiz.entities.FinanceSummary;
import eu.mgrtech.better.buiz.entities.FinancialData;

@Service
public class FinanceService {

    private final static List<String> months = List.of("January",
                                                       "February",
                                                       "March",
                                                       "April",
                                                       "May",
                                                       "June",
                                                       "July",
                                                       "August",
                                                       "September",
                                                       "October",
                                                       "November",
                                                       "December"
    );

    public FinanceSummary getSummaryByOrganization(String id) {
        return new FinanceSummary("MGRTECH srl", "BE68 5390 0754 7034", "11.945,00 €");
    }

    public List<String> getFiscalYearsForOrganization(String orgId) {
        return List.of("2024", "2023", "2022");
    }

    public FinancialData getFinancialDataByOrganizationAndFiscalYear(String orgId, String fiscalYear) {
        switch (fiscalYear) {
            case "2024":
                return new FinancialData(months.stream().limit(6).toList(),
                                         List.of(12985.65d, 10000.00d, 9857.45d, 7858.44d, 9658.32d, 5698.37d),
                                         List.of(10985.65d, 8650.24d, 5654.12d, 7854.17d, 4258.32d, 3241.02d)
                );
            case "2023":
                return new FinancialData(months,
                                         List.of(8985.65d,
                                                 5620.00d,
                                                 6502.00,
                                                 4452.44d,
                                                 9577.32d,
                                                 4215.01d,
                                                 8985.65d,
                                                 5620.00d,
                                                 6502.00,
                                                 4452.44d,
                                                 9577.32d,
                                                 4215.01d
                                         ),
                                         List.of(3250.65d,
                                                 6870.00d,
                                                 8975.45d,
                                                 6587.44d,
                                                 1054.32d,
                                                 2087.37d,
                                                 3250.65d,
                                                 6870.00d,
                                                 8975.45d,
                                                 6587.44d,
                                                 1054.32d,
                                                 2087.37d
                                         )
                );
            case "2022":
                return new FinancialData(months,
                                         List.of(1200.00d,
                                                 1520.00d,
                                                 568.45d,
                                                 2800.12d,
                                                 5630.32d,
                                                 6500.37d,
                                                 1200.00d,
                                                 1520.00d,
                                                 568.45d,
                                                 2800.12d,
                                                 5630.32d,
                                                 6500.37d
                                         ),
                                         List.of(3500.00d,
                                                 6570.00d,
                                                 1500.21d,
                                                 2100.44d,
                                                 4789.32d,
                                                 4525.37d,
                                                 3500.00d,
                                                 6570.00d,
                                                 1500.21d,
                                                 2100.44d,
                                                 4789.32d,
                                                 4525.37d
                                         )
                );
            default:
                throw new IllegalArgumentException("Invalid fiscal year: " + fiscalYear);
        }
    }

}
