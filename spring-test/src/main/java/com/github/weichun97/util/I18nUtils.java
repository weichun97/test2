package com.github.weichun97.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class I18nUtils {

    @Resource
    private MessageSource messageSource;

    /**
     * 读取无参, 无默认值的
     * @param code
     * @return
     */
    public String getMessage(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * 读取无参, 无默认值的, 手动指定语言
     * @param code
     * @return
     */
    public String getMessage(String code, Locale locale){
        return messageSource.getMessage(code, null, locale);
    }

    /**
     * 读取无参, 有默认值的
     * @param code
     * @return
     */
    public String getMessage(String code, String defaultValue){
        return messageSource.getMessage(code, null, defaultValue, LocaleContextHolder.getLocale());
    }

    /**
     * 读取无参, 有默认值的, 手动指定语言
     * @param code
     * @return
     */
    public String getMessage(String code, String defaultValue, Locale locale){
        return messageSource.getMessage(code, null, defaultValue, locale);
    }

    /**
     * 读取有参, 无默认值的
     * @param code
     * @return
     */
    public String getMessage(String code, Object[] args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * 读取有参, 无默认值的, 手动指定语言
     * @param code
     * @return
     */
    public String getMessage(String code, Object[] args, Locale locale){
        return messageSource.getMessage(code, args, locale);
    }

    /**
     * 读取有参，有默认值的
     * @param code
     * @return
     */
    public String getMessage(String code, Object[] args, String defaultValue){
        return messageSource.getMessage(code, args, defaultValue, LocaleContextHolder.getLocale());
    }

    /**
     * 读取有参，有默认值的
     * @param code
     * @return
     */
    public String getMessage(String code, Object[] args, String defaultValue, Locale locale){
        return messageSource.getMessage(code, args, defaultValue, locale);
    }
}
