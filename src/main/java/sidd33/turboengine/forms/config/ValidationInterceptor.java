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

    /*
    private void checkFileUpload(RenderingStateHolder stateHolder, HttpServletRequest request) throws Exception {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Collection<Part> parts = request.getParts();

            for (Part part : parts) {
                if (part.getContentType() != null && part.getSize() == 0) {
                    String methodName = "set" + StringUtil.capitalizeFirstLetter(part.getName());
                    Method setter = stateHolder.getFormDataClass().getMethod(methodName, MultipartFile.class);
                    setter.invoke(stateHolder.getFormData(), (MultipartFile) null);

                    System.out.println("formThen=" + stateHolder.getFormData());
                }
            }
        }
    }*/
}
