package ua.nure.kopaniev.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * This class contains common exceptions handler.
 */
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handle(HttpServletRequest req, RuntimeException ex) {
        log.error(ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
