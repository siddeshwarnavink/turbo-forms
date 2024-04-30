package sidd33.turboengine.forms.taglibs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import sidd33.turboengine.forms.data.RenderingStateHolder;

public class FormData extends SimpleTagSupport {
    private sidd33.turboengine.forms.type.FormData data;

    @Override
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        RenderingStateHolder stateHolder = (RenderingStateHolder) request.getAttribute("formState");
        if (stateHolder == null) {
            throw new JspException("RenderingState not found in request");
        }

        stateHolder.setFormDataClass(data.getClass());
        stateHolder.setFormData(data);
    }

    public sidd33.turboengine.forms.type.FormData getData() {
        return data;
    }

    public void setData(sidd33.turboengine.forms.type.FormData data) {
        this.data = data;
    }
}
