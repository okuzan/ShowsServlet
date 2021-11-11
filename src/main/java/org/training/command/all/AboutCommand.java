package org.training.command.all;

import org.training.command.Command;
import org.training.command.CommandUtility;
import org.training.model.dto.User;

import javax.servlet.http.HttpServletRequest;

public class AboutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/about.jsp";
    }
}
