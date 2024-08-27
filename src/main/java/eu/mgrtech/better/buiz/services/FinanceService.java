package eu.mgrtech.better.buiz.services;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.stereotype.Service;

import eu.mgrtech.better.buiz.entities.FinanceSummary;
import eu.mgrtech.better.buiz.entities.FinancialData;
import eu.mgrtech.better.buiz.entities.Transaction;

@Service
public class FinanceService {

    private List<String> months = Arrays.stream(Month.values()).map(String::valueOf).toList();
    private final NumberFormat euCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final List<Transaction> transactions;
    private final FinancialData financialData2024;
    private final FinancialData financialData2023;
    private final FinancialData financialData2022;

    public FinanceService() {

        financialData2024 = new FinancialData(months.stream().limit(6).toList(),
                                              List.of(12985.65d, 10000.00d, 9857.45d, 7858.44d, 9658.32d, 5698.37d),
                                              List.of(10985.65d, 8650.24d, 5654.12d, 7854.17d, 4258.32d, 3241.02d)
        );

        financialData2023 = new FinancialData(months,
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

        financialData2022 = new FinancialData(months,
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

        transactions = List.of(new Transaction(UUID.randomUUID().toString(),
                                               "Delhaize Evere",
                                               euCurrencyFormat.format(-125.0),
                                               "OUT",
                                               LocalDateTime.now().format(formatter)
                               ),
                               new Transaction(UUID.randomUUID().toString(),
                                               "PwC",
                                               euCurrencyFormat.format(11954.0),
                                               "IN",
                                               LocalDateTime.now().format(formatter)
                               ),
                               new Transaction(UUID.randomUUID().toString(),
                                               "D&G Partner",
                                               euCurrencyFormat.format(-360.80),
                                               "OUT",
                                               LocalDateTime.now().format(formatter)
                               ),
                               new Transaction(UUID.randomUUID().toString(),
                                               "Taxes",
                                               euCurrencyFormat.format(-778.50),
                                               "OUT",
                                               LocalDateTime.now().format(formatter)
                               ),
                               new Transaction(UUID.randomUUID().toString(),
                                               "Social IT",
                                               euCurrencyFormat.format(3687.81),
                                               "IN",
                                               LocalDateTime.now().format(formatter)
                               ),
                               new Transaction(UUID.randomUUID().toString(),
                                               "Manager Reimbursement",
                                               euCurrencyFormat.format(-4215.77),
                                               "OUT",
                                               LocalDateTime.now().format(formatter)
                               )
        );
    }

    public FinanceSummary getSummaryByOrganization(String id) {
        return new FinanceSummary("MGRTECH srl", "BE68 5390 0754 7034", "11.945,00 €");
    }

    public List<String> getFiscalYearsForOrganization(String orgId) {
        return List.of("2024", "2023", "2022");
    }

    public FinancialData getFinancialDataByOrganizationAndFiscalYear(String orgId, String fiscalYear) {
        return switch (fiscalYear) {
            case "2024" -> financialData2024;
            case "2023" -> financialData2023;
            case "2022" -> financialData2022;
            default -> throw new IllegalArgumentException("Invalid fiscal year: " + fiscalYear);
        };
    }

    public List<Transaction> getTransactionsFor(String orgId) {
        return transactions;
    }
}
