package sidd33.turboengine.forms.taglibs;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class Script extends SimpleTagSupport {
    public StringBuilder builder = new StringBuilder();

    public StringBuilder getBuilder() {
		return builder;
	}

	@Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write(builder.toString());
    }
}
