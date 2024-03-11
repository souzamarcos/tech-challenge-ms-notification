package com.fiap.burger.usecase.misc.profiles;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.context.annotation.Profile;

@Retention(RetentionPolicy.RUNTIME)
@Profile("prod")
public @interface Production {
}
