package sidd33.turboengine.forms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sidd33.turboengine.forms.annotation.WithFormProcessor;

@Configuration
public class AnnotationAutoConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WithFormProcessor());
    }
}
