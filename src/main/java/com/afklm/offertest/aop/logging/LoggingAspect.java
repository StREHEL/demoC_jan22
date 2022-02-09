package com.afklm.offertest.aop.logging;

import org.springframework.core.env.Environment;

public class LoggingAspect {

    private final Environment env;

    public LoggingAspect(Environment env) {
        this.env = env;
    }

}
