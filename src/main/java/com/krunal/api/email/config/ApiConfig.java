package com.krunal.api.email.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configurable
public class ApiConfig {

    final String TEMPLATE_PATH = "classpath:/templates";

    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean factoryBean(){
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath(TEMPLATE_PATH);
        return bean;
    }
}
