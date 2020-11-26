package bellintegrator.com.demo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
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
        JSONPObject jo = new JSONPObject("data", o);
        return jo;
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> notFoundException(Exception e) {
        Map<String, String> result = new HashMap<>();
        UUID errorUuid = UUID.randomUUID();
        result.put("error", e.getMessage());
        result.put("UUID", errorUuid.toString());
        LOGGER.error(errorUuid.toString());
        LOGGER.error(e.getMessage());
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        LOGGER.error(writer.toString());
        return result;
    }

}
