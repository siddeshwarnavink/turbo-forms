package sidd33.turboengine.forms.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sidd33.turboengine.forms.taglibs.Style;
import sidd33.turboengine.forms.type.FieldGenerator;
import sidd33.turboengine.forms.type.FormData;
import sidd33.turboengine.forms.type.FormFieldType;

public class WithFormProcessor implements HandlerInterceptor {
    public static Class<? extends FormData> formDataClass;
    public static List<FormField> formFields = new ArrayList<>();
    private static Set<FormFieldType> styleInitilized = new HashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            if (method.isAnnotationPresent(WithForm.class)) {
                WithForm customAnnotation = method.getAnnotation(WithForm.class);
                formDataClass = customAnnotation.value();
                extractFormFields();
            }
        }
        return true;
    }

    private void extractFormFields() {
        Field[] fields = formDataClass.getDeclaredFields();

        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == FormField.class) {
                    FormField formFieldAnnotation = (FormField) annotation; 

                    if (formFieldAnnotation.name().length() < 1) {
                        Class<? extends Annotation> annotationType = formFieldAnnotation.annotationType();
                        String annotationLabel = formFieldAnnotation.label();
                        FormFieldType annotationFormFieldType = formFieldAnnotation.fieldType();

                        formFieldAnnotation = new FormField() {
                            @Override
                            public Class<? extends Annotation> annotationType() {
                                return annotationType;
                            }

                            @Override
                            public String name() {
                                return field.getName();
                            }

                            @Override
                            public String label() {
                                return annotationLabel;
                            }

                            @Override
                            public FormFieldType fieldType() {
                                return annotationFormFieldType;
                            }
                        };
                    }

                    if (FormFieldGeneratorProcessor.generators.containsKey(formFieldAnnotation.fieldType())
                            && !styleInitilized.contains(formFieldAnnotation.fieldType())) {
                        FieldGenerator generator = FormFieldGeneratorProcessor.generators
                                .get(formFieldAnnotation.fieldType());

                        String styleRender = generator.renderStyles(formFieldAnnotation);
                        if (styleRender != null) {
                            Style.builder.append(styleRender);
                        }

                        styleInitilized.add(formFieldAnnotation.fieldType());
                    }

                    formFields.add(formFieldAnnotation);
                }
            }
        }
    }
}
