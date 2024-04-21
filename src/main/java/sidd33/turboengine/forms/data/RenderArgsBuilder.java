package sidd33.turboengine.forms.data;

import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.jsp.JspContext;
import sidd33.turboengine.forms.annotation.FormField;

public class RenderArgsBuilder {
    private RenderArgs renderArgs;

    public RenderArgsBuilder() {
        renderArgs = new RenderArgs();
    }

    public RenderArgsBuilder formField(FormField formField) {
        renderArgs.setFormField(formField);
        return this;
    }

    public RenderArgsBuilder value(Object value) {
        renderArgs.setValue(value);
        return this;
    }

    public RenderArgsBuilder config(Map<String, Object> config) {
        renderArgs.setConfig(config);
        return this;
    }

    public RenderArgsBuilder errorMessage(String errorMessage) {
        renderArgs.setErrorMessage(errorMessage);
        return this;
    }

    public RenderArgsBuilder servletContext(ServletContext servletContext) {
        renderArgs.setServletContext(servletContext);
        return this;
    }

    public RenderArgsBuilder jspContext(JspContext jspContext) {
        renderArgs.setJspContext(jspContext);
        return this;
    }

    public RenderArgsBuilder request(ServletRequest request) {
        renderArgs.setRequest(request);
        return this;
    }

    public RenderArgsBuilder response(ServletResponse response) {
        renderArgs.setResponse(response);
        return this;
    }

    public RenderArgsBuilder initialized(boolean initialized) {
        renderArgs.setInitilized(initialized);
        return this;
    }

    public RenderArgs build() {
        return renderArgs;
    }
}
