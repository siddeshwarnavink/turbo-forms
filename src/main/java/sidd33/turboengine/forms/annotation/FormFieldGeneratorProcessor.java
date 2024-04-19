package sidd33.turboengine.forms.annotation;

import java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import sidd33.turboengine.forms.data.FormFieldType;
import sidd33.turboengine.forms.type.FieldGenerator;

@Component
public class FormFieldGeneratorProcessor implements BeanPostProcessor {
    public static HashMap<FormFieldType, FieldGenerator> generators = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        
        if (clazz.isAnnotationPresent(FormFieldGenerator.class)) {
            FormFieldGenerator annotation = clazz.getAnnotation(FormFieldGenerator.class);
            generators.put(annotation.type(), (FieldGenerator) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
