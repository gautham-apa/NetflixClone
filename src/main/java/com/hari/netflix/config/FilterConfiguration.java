package com.hari.netflix.config;

import com.hari.netflix.filter.AdminActionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<AdminActionFilter> filter() {
        FilterRegistrationBean<AdminActionFilter> filterRegistrationBean = new FilterRegistrationBean<AdminActionFilter>();
        filterRegistrationBean.setFilter(new AdminActionFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/v1/admin/*"));
        return filterRegistrationBean;
    }
}
