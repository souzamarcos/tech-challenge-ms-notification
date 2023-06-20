package com.fiap.burger.domain.misc;

import com.fiap.burger.domain.misc.exception.BlankAttributeException;
import com.fiap.burger.domain.misc.exception.NegativeOrZeroValueException;
import com.fiap.burger.domain.misc.exception.NullAttributeException;

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
}
