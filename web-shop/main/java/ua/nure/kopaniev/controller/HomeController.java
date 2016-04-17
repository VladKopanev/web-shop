package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        log.info("::home()");
        return "index";
    }
}
