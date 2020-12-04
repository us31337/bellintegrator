package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.filter.OrganisationFilter;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;
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
public class OrganisationControllerTest {

    private String PREFIX;
    private TestRestTemplate restTemplate;
    private ObjectMapper objectMapper;
    @LocalServerPort
    private int port;

    @Autowired
    public OrganisationControllerTest(TestRestTemplate restTemplate,
                                      ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        this.PREFIX = "http://localhost:" + port + "/api/organisation/";
    }

    @Test
    public void shouldGetErrorAnd404Status() throws JsonProcessingException {
        Long id = 1_111_111L;
        ResponseEntity<String> response = restTemplate.getForEntity(PREFIX + id.toString(), String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.NOT_FOUND);
        JsonNode node = objectMapper.readTree(response.getBody());
        Assert.assertTrue(node.hasNonNull("error"));
    }

    @Test
    public void shouldReturnOrganisationDto() throws JsonProcessingException {
        Long id = 1L;
        final String INN = "7842499778";
        ResponseEntity<String> response = restTemplate.getForEntity(PREFIX + id.toString(), String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        SingleOrganisationDto dto = objectMapper.treeToValue(data, SingleOrganisationDto.class);
        Assert.assertTrue(dto.getInn().equals(INN));
    }

    @Test
    public void shouldGetInternalServerErrorDueToWrongKPP() {
        SaveOrganisationDto org = new SaveOrganisationDto();
        org.setInn("2370007068");
        org.setAddress("353800, Краснодарский край, ст-ца Полтавская, ул М.Горького, д 5");
        org.setKpp("237001001000"); //KPP is too long
        org.setName("АО \"ВЕКТОР\"");
        org.setFullName("АКЦИОНЕРНОЕ ОБЩЕСТВО \"ВЕКТОР\"");
        org.setPhone("+74991927500");
        org.setIsActive(true);

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "save", org, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void shouldSuccessfullySaveNewOrganisation() {
        SaveOrganisationDto org = new SaveOrganisationDto();
        org.setInn("2370007068");
        org.setAddress("353800, Краснодарский край, ст-ца Полтавская, ул М.Горького, д 5");
        org.setKpp("237001001");
        org.setName("АО \"ВЕКТОР\"");
        org.setFullName("АКЦИОНЕРНОЕ ОБЩЕСТВО \"ВЕКТОР\"");
        org.setPhone("+74991927500");
        org.setIsActive(true);

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "save", org, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void shouldSuccessfullyUpdateOrganisation() throws JsonProcessingException {
        UpdateOrganisationDto orgDto = new UpdateOrganisationDto();
        orgDto.setId(3L);
        orgDto.setName("Тест");
        orgDto.setFullName("Проверочная проверка");
        orgDto.setInn("7825061984");
        orgDto.setKpp("781301001");
        orgDto.setAddress("197101, г Санкт-Петербург, Каменноостровский пр-кт, д 12 литер а");
        orgDto.setIsActive(true);

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "update", orgDto, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);

    }

    @Test
    public void shouldReturnOkStatusAndNonEmptyOrgList() throws JsonProcessingException {
        OrganisationFilter filter = new OrganisationFilter();
        filter.setIsActive(true);
        filter.setName("аналит");

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "list", filter, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        SingleOrganisationDto[] dtoArray = objectMapper.treeToValue(data, SingleOrganisationDto[].class);
        Assert.assertTrue(dtoArray.length > 0);
    }
}
