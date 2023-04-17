package com.github.weichun97.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class I18nConfig {

    @Bean
    public LocaleResolver localeResolver(){
        return new ParamLocaleResolver();
    }

    /**
     * 读取请求参数的国际化解析器
     */
    private static class ParamLocaleResolver implements LocaleResolver {

        /**
         * 国际化的请求参数
         */
        private static final String PARAM = "lang";

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String l = request.getParameter(PARAM);
            Locale locale = Locale.getDefault();
            if(!StringUtils.isEmpty(l)){
                String[] split = l.split("_");
                if(split.length != 2){
                    throw new RuntimeException("语言格式错误");
                }
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }
}
