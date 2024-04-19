package sidd33.turboengine.forms.annotation;

import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sidd33.turboengine.forms.data.FormData;

public class WithFormProcessor implements HandlerInterceptor {
    public static Class<? extends FormData> formDataClass;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("RAN!");

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(WithForm.class)) {
                WithForm customAnnotation = method.getAnnotation(WithForm.class);
                formDataClass = customAnnotation.value();
            }
        }
        return true;
    }
}
