package com.fiap.burger.usecase.misc.exception;

import java.util.Map;

public class BlankAttributeException extends DomainException {

    public BlankAttributeException(String attribute) {
        super("Attribute `" + attribute + "` can not be blank.", Map.of("attribute", attribute));
    }
}
