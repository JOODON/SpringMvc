package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//MVC를 구현하기전에 컨피그를 상속 받아서 오버라이드 하는방식으로 사용!
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ("mvcexam.controller"))
public class WebMvcContestConfiguration extends WebMvcConfigurerAdapter {
    //css 이미 js등등의 것들도 같이 호환 시켜주려고 함
    public void addResoureceHamdlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
    //디폴트서블릿 핸들러를 사용하게! 매핑정보가 없는것은 핸들러가 사용하도록 해주는거
    @Override
    public void  configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }
    //특정 url의 클래스를 가져오는것
    public void addViewControllers(final ViewControllerRegistry registry){
        System.out.println("addViewControllers 가 호출 됩니다");
        registry.addViewController("/").setViewName("main");
    }
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("(/WEB-INF/views/");
        resolver.setSuffix(".jsp");//가장 마지막에 main을 붙혀달라는것
        return resolver;
    }
}
