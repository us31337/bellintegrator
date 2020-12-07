package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.entity.Country;
import bellintegrator.com.demo.entity.DocumentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ReferenceBookControllerTest {
    private String PREFIX;
    private TestRestTemplate restTemplate;
    private ObjectMapper objectMapper;
    @LocalServerPort
    private int port;

    @Autowired
    public ReferenceBookControllerTest(TestRestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        this.PREFIX = "http://localhost:" + port + "/api/";
    }

    @Test
    public void shouldReturnCountryRussia() throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(PREFIX + "countries", String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        Country[] list = objectMapper.treeToValue(data, Country[].class);
        Optional<Country> russia = Arrays.stream(list)
                .filter(country -> country.getCode() == 643).findFirst();
        Assert.assertTrue(russia.get().getName().equals("Россия"));
    }

    @Test
    public void shouldReturnPassport() throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(PREFIX + "docs", String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        DocumentType[] types = objectMapper.treeToValue(data, DocumentType[].class);
        Optional<DocumentType> type = Arrays.stream(types)
                .filter(documentType -> documentType.getCode().equals("21")).findFirst();
        Assert.assertTrue(type.get().getName().equals("Паспорт гражданина Российской Федерации"));
    }

}
