package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        log.info("::logout()");
        new SecurityContextLogoutHandler()
                .logout(req, resp, SecurityContextHolder.getContext().getAuthentication());
        log.info("redirecting to login page");
        return "redirect:/login";
    }
}
