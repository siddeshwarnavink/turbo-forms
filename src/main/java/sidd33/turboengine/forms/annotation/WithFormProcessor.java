package sidd33.turboengine.forms.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sidd33.turboengine.forms.data.FormData;

public class WithFormProcessor implements HandlerInterceptor {
    public static Class<? extends FormData> formDataClass;
    public static List<FormField> formFields = new ArrayList<>();

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
                    formFields.add(formFieldAnnotation);
                }
            }
        }
    }
}
