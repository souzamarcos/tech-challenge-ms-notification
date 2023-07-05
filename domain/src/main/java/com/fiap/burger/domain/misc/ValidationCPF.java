package com.fiap.burger.domain.misc;

import com.fiap.burger.domain.misc.exception.InvalidCPFException;

public class ValidationCPF {

    public static void validateCPF(String CPF) throws InvalidCPFException {
        if (containsNonNumericCharacters(CPF) || isInvalid(CPF))
            throw new InvalidCPFException();
    }

    private static boolean containsNonNumericCharacters(String CPF) {
        return CPF.matches(".*[^0-9].*");
    }

    private static boolean isInvalid(String CPF) {
        CPF = CPF.replaceAll("[^0-9]", "");
        if (CPF.length() != 11 || hasSameDigit(CPF))
            return true;

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(CPF.charAt(i));
        }

        return !validateDigits(digits);
    }

    private static boolean hasSameDigit(String CPF) {
        char firstDigit = CPF.charAt(0);
        for (int i = 1; i < 11; i++) {
            if (CPF.charAt(i) != firstDigit)
                return false;
        }
        return true;
    }

    private static boolean validateDigits(int[] digits) {
        int sum = 0;
        int weight = 10;

        for (int i = 0; i < 9; i++) {
            sum += digits[i] * weight;
            weight--;
        }

        int firstDigitVerifier = 11 - (sum % 11);
        if (firstDigitVerifier >= 10)
            firstDigitVerifier = 0;

        sum = 0;
        weight = 11;

        for (int i = 0; i < 10; i++) {
            sum += digits[i] * weight;
            weight--;
        }

        int secondDigitVerifier = 11 - (sum % 11);
        if (secondDigitVerifier >= 10)
            secondDigitVerifier = 0;

        return digits[9] == firstDigitVerifier && digits[10] == secondDigitVerifier;
    }
}
