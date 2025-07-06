package com.bytecron.labs.controlleradvice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFound {
    @ExceptionHandler(Exception.class)
    public String handle404(Exception ex, Model model) {

        return "not-found";
    }
}
