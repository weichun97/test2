package com.github.weichun97.controller;

import com.github.weichun97.util.I18nUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("i18n")
public class I18nController {

    @Resource
    private MessageSource messageSource;
    @Resource
    private I18nUtils i18nUtils;

    @GetMapping("test")
    public String test(){
        return i18nUtils.getMessage("test");
//        return messageSource.getMessage("test", null, LocaleContextHolder.getLocale());
    }
}
