package eu.mgrtech.better.buiz.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy =  {VatNumberValidator.class})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface VATNumber {

    String message() default "'${validatedValue}' is not valid. " +
            "A VAT Number is composed by 8 characters and the first 2 must contain the country code. " +
            "Valid Example: BE123456";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
