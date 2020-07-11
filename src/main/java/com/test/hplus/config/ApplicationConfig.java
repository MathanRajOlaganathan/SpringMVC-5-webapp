package com.test.hplus.config;

import com.test.hplus.converters.StringToEnumConvertor;
import com.test.hplus.interceptors.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

import java.util.Locale;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@ComponentScan(basePackages = "com.test")
public class ApplicationConfig  extends WebMvcConfigurationSupport {

//    Map Static Resources in the context Configuration class
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**","images/**")
                .addResourceLocations("classpath:/static/css/","classpath:/static/images/");
    }

//    Add View Resolver in the context Configuration for the JSPs
//
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(2);
        return viewResolver;
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConvertor());
    }

    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setThreadNamePrefix("hplusapp-thread");
        return threadPoolExecutor;
    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(asyncTaskExecutor());
    }

    @Bean
    public XmlViewResolver xmlViewResolver()
    {
        XmlViewResolver xmlViewResolver = new XmlViewResolver();
        xmlViewResolver.setLocation(new ClassPathResource("views.xml"));
        xmlViewResolver.setOrder(1);
        return xmlViewResolver;
    }

//    @Bean
//    public ResourceBundleViewResolver resourceBundleViewResolver()
//    {
//        ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
//        resourceBundleViewResolver.setBasename("views");
//        return  resourceBundleViewResolver;
//    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new ThemeChangeInterceptor());//Theme
        registry.addInterceptor(new LocaleChangeInterceptor());//locale
//        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/goToLogin");
    }

    @Bean
    public ThemeResolver themeResolver()
    {
        System.out.println("Inside ThemeResolver");
        CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
        cookieThemeResolver.setDefaultThemeName("client-theme1");
        cookieThemeResolver.setCookieName("theme");
        return cookieThemeResolver;
    }

    @Bean
    public LocaleResolver localeResolver()
    {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.UK);
        cookieLocaleResolver.setCookieName("locale");
        return  cookieLocaleResolver;
    }
}
