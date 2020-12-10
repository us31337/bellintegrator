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

/**
 * Класс отвечающий за внешний вид ответа сервера и логгирование ошибок
 */
@RestControllerAdvice(basePackages = "bellintegrator.com.demo.controller")
public class ResponseAdvisor implements ResponseBodyAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger("Rest controller advice");
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        ExceptionHandler annotation = methodParameter.getMethod().getAnnotation(ExceptionHandler.class);
        //All works with ExceptionHandlers ignores here
        return (annotation == null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseView responseView = new ResponseView();
        responseView.setData(o);
        serverHttpResponse.setStatusCode(HttpStatus.OK);
        return responseView;
    }

    /**
     * Обработчик исключений
     *
     * @param e исключений
     * @return сообщение об ошибке и http код
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity generalExceptionHandler(Exception e) {
        UUID errorUuid = UUID.randomUUID();
        Map<String, String> map = new HashMap<>();
        map.put("error", errorUuid.toString());

        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.stream().forEach(err -> {
                String message = err.getDefaultMessage();
                map.merge("error", message, (oldS, newS) -> oldS + "; " + newS);
            });
            LOGGER.error(errorUuid.toString() + "\n" +
                    map.get("error") + "\n", e);
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        } else if (e instanceof DataIntegrityViolationException) {
            Throwable cause = e;
            do {
                cause = cause.getCause();
            } while (!cause.getClass().getSimpleName().equals("JdbcSQLDataException"));
            String message = cause.getMessage();
            LOGGER.error(errorUuid.toString() + "\n" +
                    message + "\n", e);
            map.merge("error", e.getMessage(), (oldS, newS) -> oldS + "; " + newS);
            return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (e instanceof NotFoundException) {
            String message = e.getMessage();
            LOGGER.error(errorUuid.toString() + "\n" +
                    message + "\n", e);
            map.merge("error", message, (oldS, newS) -> oldS + "; " + newS);
            return new ResponseEntity(map, HttpStatus.NOT_FOUND);
        } else {
            String message = e.getMessage();
            LOGGER.error(errorUuid.toString() + "\n" +
                    message + "\n", e);
            map.merge("error", "Internal server error, see logs", (oldS, newS) -> oldS + "; " + newS);
            return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
