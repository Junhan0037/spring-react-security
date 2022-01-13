package com.springreactsecurity.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = {"/"}, method= {RequestMethod.GET, RequestMethod.POST})
    public String handleError() {
        return "/index.html";
    }

}
