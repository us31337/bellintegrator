package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.ResponseView;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger("Rest controller advice");

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        ExceptionHandler annotation = methodParameter.getMethod().getAnnotation(ExceptionHandler.class);
        //All works with ExceptionHandlers ignores here
        return annotation == null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseView responseView = new ResponseView();
        responseView.setData(o);
        ResponseEntity entity = new ResponseEntity(responseView, HttpStatus.OK);
        return entity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity generalExceptionHandler(Exception e) {
        UUID errorUuid = UUID.randomUUID();
        Map<String, String> map = new HashMap<>();
        map.put("error", errorUuid.toString());
        LOGGER.error(errorUuid.toString());

        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.stream().forEach(err -> {
                String message = err.getDefaultMessage();
                LOGGER.error(message);
                map.merge("error", message, (oldS, newS) -> oldS + "; " + newS);
            });
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        } else if (e instanceof DataIntegrityViolationException) {
            Throwable cause = e;
            do {
                cause = cause.getCause();
            } while (!cause.getClass().getSimpleName().equals("JdbcSQLDataException"));
            String message = cause.getMessage();
            LOGGER.error(message);
            map.merge("error", message, (oldS, newS) -> oldS + "; " + newS);
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        } else if (e instanceof NotFoundException) {
            String message = e.getMessage();
            LOGGER.error(message);
            map.merge("error", message, (oldS, newS) -> oldS + "; " + newS);
            return new ResponseEntity(map, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(map, HttpStatus.OK);
    }

}
