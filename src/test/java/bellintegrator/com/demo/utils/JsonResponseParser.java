package bellintegrator.com.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

public class JsonResponseParser {

    private ObjectMapper objectMapper;

    public JsonResponseParser() {
        this.objectMapper = new ObjectMapper();
    }

    public boolean responseHasError(String response) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(response);
        boolean error = jsonNode.hasNonNull("error");
        return error;
    }

    public HttpStatus getHttpStatus(String response) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(response);
        return HttpStatus.OK;
    }


}
