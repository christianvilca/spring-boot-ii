package es.edu.escuela_it.microservices.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CIFValidator.class) // clase java a validar
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CIF {
    String message() default "Invalid CIF Number"; // Mensaje cuando no se cumpla con la validacion

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
