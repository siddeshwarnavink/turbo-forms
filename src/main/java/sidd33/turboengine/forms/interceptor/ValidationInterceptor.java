package sidd33.turboengine.forms.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sidd33.turboengine.forms.data.RenderingStateHolder;

public class ValidationInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        if(modelAndView != null && modelAndView.getModel() != null) {
            RenderingStateHolder.setModel(modelAndView.getModel());
        }

    }
}
