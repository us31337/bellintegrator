package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.utils.JsonResponseParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringRestOrganisationController {

    private String PREFIX;
    private TestRestTemplate restTemplate;
    private ObjectMapper objectMapper;
    @LocalServerPort
    private int port;
    private JsonResponseParser parser;

    @Autowired
    public SpringRestOrganisationController(TestRestTemplate restTemplate,
                                            ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.parser = new JsonResponseParser();
    }

    @BeforeEach
    void setUp() {
        this.PREFIX = "http://localhost:" + port + "/api/organisation/";
    }

    @Test
    void getOrganisationById() throws IOException {
        Long id = 1111L;
        String response = restTemplate.getForObject(PREFIX + id.toString(), String.class);
        restTemplate.execute(PREFIX + id.toString(), HttpMethod.GET, )
        parser.responseHasError(response);
        parser.getHttpStatus(response);
//        SingleOrganisationDto organisationDto = objectMapper.treeToValue(body, SingleOrganisationDto.class);
    }
}
