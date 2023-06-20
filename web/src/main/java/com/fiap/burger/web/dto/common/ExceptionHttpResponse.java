package com.fiap.burger.web.dto.common;

import ch.qos.logback.core.joran.sanity.Pair;
import com.fiap.burger.domain.misc.exception.DomainException;
import java.util.Set;
import org.springframework.http.HttpStatus;

public class ExceptionHttpResponse {

//    var exceptions = new Set<Pair<Throwable, HttpStatus>>(Pair())
    public static HttpStatus getHttpStatusBy(Exception exception) {
        if (exception instanceof DomainException) return HttpStatus.BAD_REQUEST;
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
