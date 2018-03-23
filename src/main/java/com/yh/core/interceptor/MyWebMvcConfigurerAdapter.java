package com.yh.core.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableWebMvc
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    public void addInterceptors(ViewControllerRegistry  registry) {
       /* registry.addInterceptor(loginInterceptor());
        super.addInterceptors(registry);
        super.addInterceptors(registry);*/
    	 registry.addViewController("/error").setViewName("404.html");
         registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
         super.addViewControllers(registry);
    }
    
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
        super.configurePathMatch(configurer);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/access-token");

        super.addInterceptors(registry);

       // System.out.println("开始开始咯。。。。");
    }

}

