package com.coordinator.core.general.main.Interceptors;

import com.coordinator.core.general.main.models.ExceptionMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            NullPointerException.class
    })
    public ResponseEntity<Object> handleAllExceptionMethod(Exception ex, WebRequest requset) {

        ExceptionMessage exceptionMessage = new ExceptionMessage();

        // Handle All Field Validation Errors
        if(ex instanceof MethodArgumentNotValidException) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb.append(fieldError.getDefaultMessage());
                sb.append(";");
            }
            exceptionMessage.setMessage(sb.toString());
        }else{
            exceptionMessage.setMessage(ex.getLocalizedMessage());
        }

        exceptionMessage.setError(ex.getClass().getCanonicalName());
        exceptionMessage.setPath(((ServletWebRequest) requset).getRequest().getServletPath());

        // return exceptionMessageObj;
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}