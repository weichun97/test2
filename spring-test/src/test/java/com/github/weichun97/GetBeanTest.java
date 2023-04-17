package com.github.weichun97;

import com.github.weichun97.config.AnnotationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetBeanTest {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test(){
        Object lion = applicationContext.getBean("lion");
        assertEquals(AnnotationConfig.Lion.class, lion.getClass());

        AnnotationConfig.Lion lion2 = applicationContext.getBean("lion", AnnotationConfig.Lion.class);
        assertEquals(lion, lion2);

        AnnotationConfig.Lion lion3 = applicationContext.getBean(AnnotationConfig.Lion.class);
        assertEquals(lion2, lion3);

        assertThrows(ClassCastException.class, () -> {
            AnnotationConfig.Tiger tiger = (AnnotationConfig.Tiger) applicationContext.getBean("lion");
        });

        assertThrows(NoSuchBeanDefinitionException.class, () ->
                applicationContext.getBean(AnnotationConfig.Animal.class));
    }

    @Test
    public void test2(){
        AnnotationConfig.Tiger tiger = (AnnotationConfig.Tiger) applicationContext.getBean("tiger", "Siberian");
        AnnotationConfig.Tiger secondTiger = (AnnotationConfig.Tiger) applicationContext.getBean("tiger", "Striped");
        assertEquals("Siberian", tiger.getName());
        assertEquals("Striped", secondTiger.getName());

        AnnotationConfig.Tiger tiger3 = applicationContext.getBean(AnnotationConfig.Tiger.class, "Shere Khan");
        assertEquals("Shere Khan", tiger3.getName());
    }
}
