package sidd33.turboengine.forms.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sidd33.turboengine.forms.data.RenderingStateHolder;
import sidd33.turboengine.forms.type.FormData;

public class ValidationInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView)
            throws Exception {
        RenderingStateHolder stateHolder = (RenderingStateHolder) request.getAttribute("formState");
        if (modelAndView != null && modelAndView.getModel() != null && stateHolder != null) {
            stateHolder.setModel(modelAndView.getModel());

            Class<? extends FormData> formDataClass = stateHolder.getFormDataClass();
            if (formDataClass != null) {
                for (Object modelValue : modelAndView.getModel().values()) {
                    if (formDataClass.isInstance(modelValue)) {
                        stateHolder.setFormData(modelValue);
                    }
                    break;
                }
            }
        }
    }
}
