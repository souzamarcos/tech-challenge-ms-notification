package com.fiap.burger.web.dto.common;

import com.fiap.burger.domain.misc.exception.DomainException;
import com.fiap.burger.domain.misc.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;

public class ExceptionHttpResponse {

    public static HttpStatus getHttpStatusBy(Exception exception) {
        if (exception instanceof ProductNotFoundException) return HttpStatus.NOT_FOUND;
        if (exception instanceof DomainException) return HttpStatus.BAD_REQUEST;
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
