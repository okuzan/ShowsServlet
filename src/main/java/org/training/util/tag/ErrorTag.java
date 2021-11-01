package org.training.util.tag;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public class ErrorTag extends TagSupport {
    private String field;

    public void setField(String field) {
        this.field = field;
    }


    public int doStartTag() throws JspException {
        try {
            String message = (String) pageContext.findAttribute(field);
            if (message != null) {
                JspWriter out = pageContext.getOut();
                out.println(" <p class=\"error-message\">" + message + "</p>");
            }

        } catch (IOException ioe) {
            throw new JspException("Error: " + ioe.getMessage());
        }
        return EVAL_PAGE;
    }


    public int doEndTag() throws JspException {
        return SKIP_BODY;
    }
}
