package eu.mgrtech.better.buiz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Transaction {

    private String id;
    private String description;
    private String amount;
    private String type;
    private String creationDate;
}
