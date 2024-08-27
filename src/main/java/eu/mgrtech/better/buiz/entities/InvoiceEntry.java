package eu.mgrtech.better.buiz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class InvoiceEntry {

    private String id = UUID.randomUUID().toString();
    private String description;
    private double unit;
    private double unitPrice;
    private double amount;
    private double vat;

    public boolean isEmpty() {
        return StringUtils.isBlank(description) && unit == 0 && vat == 0;
    }

    public void calculateAmount() {
        double amountNoVat = unit * unitPrice;
        double amountVat = amountNoVat * (vat/100);
        amount = new BigDecimal(amountNoVat + amountVat).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
