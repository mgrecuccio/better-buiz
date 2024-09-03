package eu.mgrtech.better.buiz.entities;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialData {

    private List<String> months;
    private List<Double> incomes;
    private List<Double> expenses;

}
