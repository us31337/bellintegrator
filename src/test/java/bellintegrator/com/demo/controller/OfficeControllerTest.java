package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.filter.OfficeFilter;
import bellintegrator.com.demo.view.officedto.ListOfficeDto;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.SingleOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OfficeControllerTest {

    private String PREFIX;
    private TestRestTemplate restTemplate;
    private ObjectMapper objectMapper;
    @LocalServerPort
    private int port;

    @Autowired
    public OfficeControllerTest(TestRestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        this.PREFIX = "http://localhost:" + port + "/api/office/";
    }

    @Test
    public void saveNewOffice() throws IOException {
        final String TEST_OFFICE_NAME = "Офис для теста";
        final Long TEST_ORG_ID = 1L;

        SaveOfficeDto officeDto = new SaveOfficeDto();
        officeDto.setOrgId(TEST_ORG_ID);
        officeDto.setName(TEST_OFFICE_NAME);
        officeDto.setIsActive(true);
        officeDto.setPhone("98544313454");
        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "save", officeDto, String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        Assert.assertTrue(data.get("result").asText().equals("success"));
        OfficeFilter filter = new OfficeFilter();
        filter.setOrgId(TEST_ORG_ID);
        filter.setName(TEST_OFFICE_NAME);
        Optional<ListOfficeDto> dto = getOfficeListByFilter(filter);
        Assert.assertTrue(dto.get().getName().equals(TEST_OFFICE_NAME));
    }

    @Test
    public void getOfficeList() throws IOException {
        final String TEST_OFFICE_NAME = "Клининг"; //from data.sql
        final Long TEST_ORG_ID = 1L;

        OfficeFilter filter = new OfficeFilter();
        filter.setOrgId(TEST_ORG_ID);
        filter.setName(TEST_OFFICE_NAME);

        Optional<ListOfficeDto> first = getOfficeListByFilter(filter);
        Assert.assertTrue(first.isPresent());
        SingleOfficeDto office = getOfficeDtoById(first.get().getId());
        Assert.assertTrue(office.getName().equals(TEST_OFFICE_NAME));
    }

    private Optional<ListOfficeDto> getOfficeListByFilter(OfficeFilter filter) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "list", filter, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        ListOfficeDto[] officeDtos = objectMapper.treeToValue(data, ListOfficeDto[].class);
        Optional<ListOfficeDto> first = Arrays.stream(officeDtos).
                filter(listOfficeDto -> listOfficeDto.getName().equals(filter.getName()))
                .findFirst();
        return first;
    }


    @Test
    void updateOffice() throws IOException {
        Long id = 3L;
        String updateString = "Строка для обновления";
        UpdateOfficeDto officeDto = new UpdateOfficeDto();
        officeDto.setId(id); // from previous tests
        officeDto.setIsActive(false);
        officeDto.setAddress(updateString);

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "update", officeDto, String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        Assert.assertTrue(data.get("result").asText().equals("success"));
        SingleOfficeDto office = getOfficeDtoById(id);
        Assert.assertTrue(office.getAddress().equals(updateString));
    }

    @Test
    public void getOfficeById() throws JsonProcessingException {
        long id = 3L; //office Id from data.sql
        final String OFFICE_NAME = "Отдел рекламаций";

        SingleOfficeDto officeDto = getOfficeDtoById(id);
        Assert.assertTrue(officeDto.getName().equals(OFFICE_NAME));
    }

    private SingleOfficeDto getOfficeDtoById(long id) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(PREFIX + id, String.class);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        SingleOfficeDto officeDto = objectMapper.treeToValue(data, SingleOfficeDto.class);
        return officeDto;
    }
}