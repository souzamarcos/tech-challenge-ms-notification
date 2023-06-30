package com.fiap.burger.domain.misc;

import com.fiap.burger.domain.misc.exception.*;

import java.util.regex.Pattern;

public class ValidationUtils {
    public static void validateNotNull(Object value, String attributeName) {
        if(value == null) throw new NullAttributeException(attributeName);
    }

    public static void validateNotBlank(String value, String attributeName) {
        if(value.isBlank()) throw new BlankAttributeException(attributeName);
    }
    public static void validatePositiveNotZero(Double value, String attributeName) {
        if(value <= 0 ) throw new NegativeOrZeroValueException(attributeName);
    }

    public static void validateEmailFormat(String email, String attributeName) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new InvalidEmailFormatException();
        }
    }

    public static void validateCPF(String cpf, String attributeName) {
        if (cpf.length() != 11) {
            throw new InvalidCPFException();
        }
    }
}
