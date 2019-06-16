package com.zipe.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class BaseService {

    @Autowired
    private MessageSource messageSource;

    protected String getMessage(String str) {
        return messageSource.getMessage(str, null, Locale.TAIWAN);
    }

}
