package sidd33.turboengine.forms.taglibs;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import sidd33.turboengine.forms.data.RenderingStateHolder;

public class Style extends SimpleTagSupport {
    public StringBuilder builder = new StringBuilder();

    public StringBuilder getBuilder() {
		return builder;
	}

	@Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        RenderingStateHolder stateHolder = (RenderingStateHolder) request.getAttribute("formState");
        if(stateHolder == null) {
            throw new JspException("RenderingState not found in request");
        }

        getJspContext().getOut().write(stateHolder.getStyle().getBuilder().toString());
    }
}
