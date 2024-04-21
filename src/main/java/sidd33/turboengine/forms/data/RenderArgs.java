package sidd33.turboengine.forms.data;

import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.jsp.JspContext;
import sidd33.turboengine.forms.annotation.FormField;

public class RenderArgs {
    private FormField formField;
    private Object value;
    private Map<String, Object> config;
    private String errorMessage;
    private ServletContext servletContext;
    private JspContext jspContext;
    private ServletRequest request;
    private ServletResponse response;
    private boolean initilized;

    public boolean isInitilized() {
        return initilized;
    }

    public void setInitilized(boolean initilized) {
        this.initilized = initilized;
    }

    public ServletRequest getRequest() {
        return request;
    }

    public void setRequest(ServletRequest request) {
        this.request = request;
    }

    public ServletResponse getResponse() {
        return response;
    }

    public void setResponse(ServletResponse response) {
        this.response = response;
    }

    public JspContext getJspContext() {
        return jspContext;
    }

    public void setJspContext(JspContext jspContext) {
        this.jspContext = jspContext;
    }

    public FormField getFormField() {
        return formField;
    }

    public void setFormField(FormField formField) {
        this.formField = formField;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
