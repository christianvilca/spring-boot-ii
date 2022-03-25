package es.edu.escuela_it.microservices.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CIFValidator implements ConstraintValidator<CIF, String> { // se pasa el validador que es y el tipo de entrada
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // Logica de validacion
        // que no debe ser null y tien que tener 9 caracteres

        if (value == null) {
            return false;
        }
        return value.length() == 9;
    }
}