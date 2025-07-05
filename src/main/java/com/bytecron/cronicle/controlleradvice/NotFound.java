package com.bytecron.cronicle.controlleradvice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class NotFound {
    @ExceptionHandler(Exception.class)
    public String handle404(Exception ex, Model model) {

        return "not-found";
    }
}
