package com.github.weichun97;

import com.github.weichun97.util.I18nUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import java.util.Locale;

@SpringBootTest
public class I18nTest {

    @Resource
    private MessageSource messageSource;
    @Resource
    private I18nUtils i18nUtils;


    @Test
    public void test(){
        Assert.assertEquals("test", messageSource.getMessage("test", null, new Locale("en", "US")));
        Assert.assertEquals("用户[张三]不存在。", messageSource.getMessage("noUser", new Object[]{"张三"}, new Locale("zh", "CN")));
        Assert.assertEquals("user [张三] not exists.", messageSource.getMessage("noUser", new Object[]{"张三"}, new Locale("en", "US")));
        Assert.assertEquals("通用", messageSource.getMessage("common", null, new Locale("en", "US")));
        Assert.assertEquals("默认值", messageSource.getMessage("apple", null, "默认值", new Locale("en", "US")));

        Assert.assertEquals("test", i18nUtils.getMessage("test", new Locale("en", "US")));
        Assert.assertEquals("用户[张三]不存在。", i18nUtils.getMessage("noUser", new Object[]{"张三"}, new Locale("zh", "CN")));
        Assert.assertEquals("user [张三] not exists.", i18nUtils.getMessage("noUser", new Object[]{"张三"}, new Locale("en", "US")));
        Assert.assertEquals("通用", i18nUtils.getMessage("common", new Locale("en", "US")));
        Assert.assertEquals("默认值", i18nUtils.getMessage("apple", "默认值", new Locale("en", "US")));

    }
}
