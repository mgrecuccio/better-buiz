package eu.mgrtech.better.buiz.views.clients.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class VatNumberValidator implements ConstraintValidator<VATNumber, String> {

    @Override
    public boolean isValid(String inputVatNumber, ConstraintValidatorContext constraintValidatorContext) {
        return inputVatNumber.length() == 8;
    }
}
