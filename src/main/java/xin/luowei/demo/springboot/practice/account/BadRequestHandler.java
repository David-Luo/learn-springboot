package xin.luowei.demo.springboot.practice.account;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class BadRequestHandler {

    private final static int VALIDATION_FAILED_CODE = 1;
    private final static int BIND_FAILED_CODE = 2;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object excetpion(final MethodArgumentNotValidException ex, final ServletWebRequest req,
            final HandlerMethod handlerMethod) {
        BindingResult bindingResult = ex.getBindingResult();
        log.info("{} 请求参数异常:{}", req.getRequest().getServletPath(), bindingResult.getFieldErrors());

        Map<String, Object> view = new HashMap<>();
        view.put("code", VALIDATION_FAILED_CODE);
        FieldError fieldError = bindingResult.getFieldError();
        view.put("field", fieldError.getField());
        view.put("message", fieldError.getDefaultMessage());

        return view;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object excetpion(final ConstraintViolationException ex, final ServletWebRequest req,
            final HandlerMethod handlerMethod) {
        log.info("{} 请求参数异常:{}", req.getRequest().getServletPath(), ex.getConstraintViolations());

        ConstraintViolation<?> cv = ex.getConstraintViolations().iterator().next();
        String message = (cv == null)?"参数格式不对":cv.getMessage();
        Map<String, Object> view = new HashMap<>();
        view.put("code", VALIDATION_FAILED_CODE);
        view.put("message", message);

        return view;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object validExceptionHandler(final BindException ex, final ServletWebRequest req ){
        log.info("{} 请求参数异常:{}" , req.getRequest().getServletPath() , ex.getFieldErrors());
        
        Map<String, Object> view = new HashMap<>();
        view.put("code", BIND_FAILED_CODE);
        FieldError fieldError = ex.getFieldError();
        view.put("field", fieldError.getField());
        view.put("message", fieldError.getDefaultMessage());

        return view;
    }
}