package org.training.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class InfoTag extends TagSupport {
    private String cause;
    private boolean danger = false;

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setDanger(boolean d) {
        this.danger = d;
    }


    public int doStartTag() throws JspException {
        try {
            String message = (String) pageContext.findAttribute(this.cause);
            if (message != null) {
                JspWriter out = pageContext.getOut();
                out.println(danger ? " <div class=\"alert alert-danger\">" + message + "</div>\n" : " <div class=\"alert alert-info\">" + message + "</div>\n");
            }

        } catch (IOException ioe) {
            throw new JspException("Error: " + ioe.getMessage());
        }
        return EVAL_PAGE;
    }


    public int doEndTag() {
        return SKIP_BODY;
    }
}
