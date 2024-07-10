package eu.mgrtech.better.buiz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceSummary {

    private String bankAccountName;
    private String bankAccountNumber;
    private String bankAccountBalance;

}
