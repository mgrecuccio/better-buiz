package eu.mgrtech.better.buiz.views.invoicing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntry {

    private String description;
    private String occurrence;

}
