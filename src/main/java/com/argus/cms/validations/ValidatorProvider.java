package com.argus.cms.validations;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorProvider {

    private static Validator validator;

    @Autowired
    private Validator injectedValidator;

    @PostConstruct
    public void init() {
        ValidatorProvider.validator = this.injectedValidator;
    }
    public static Validator getValidator() {
        return validator;
    }
}