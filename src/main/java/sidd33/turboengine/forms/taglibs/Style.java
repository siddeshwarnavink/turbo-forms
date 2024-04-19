package sidd33.turboengine.forms.taglibs;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class Style extends SimpleTagSupport {
    public static StringBuilder builder = new StringBuilder();

    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write(builder.toString());
    }
}
