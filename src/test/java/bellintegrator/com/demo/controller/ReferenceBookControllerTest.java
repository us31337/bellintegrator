package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.reference.CountryReferenceView;
import bellintegrator.com.demo.view.reference.DocumentReferenceView;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    public void shouldReturnCountryUSA() throws JsonProcessingException {
        CountryReferenceView countryView = new CountryReferenceView();
        countryView.setCode(840);

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "countries", countryView, String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        Assert.assertTrue(data.get("name").asText().equals("США"));
        System.out.println(data);
    }

    @Test
    public void shouldReturnPassport() throws JsonProcessingException {
        DocumentReferenceView docView = new DocumentReferenceView();
        docView.setCode("21");

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "docs", docView, String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        Assert.assertTrue(data.get("name").asText().equals("Паспорт гражданина Российской Федерации"));
        System.out.println(data);
    }

}
