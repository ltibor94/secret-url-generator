package com.tibor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.util.Arrays;

@Configuration
public class ApplicationConfig {

    @Bean
    public ViewResolver contentNegotiatingViewResolver() {
        var resolver = new ContentNegotiatingViewResolver();

        resolver.setDefaultViews(Arrays.asList(new MappingJackson2XmlView(), new MappingJackson2JsonView()));
        return resolver;
    }

}
