package sidd33.turboengine.forms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sidd33.turboengine.forms.type.FormFieldType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormFieldGenerator {
    FormFieldType type();
}
