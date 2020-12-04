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
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OfficeControllerTest {

    private final String TEST_OFFICE_NAME = "Офис для теста";
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
    @Order(1)
    public void saveNewOffice() throws IOException {
        SaveOfficeDto officeDto = new SaveOfficeDto();
        officeDto.setOrgId(1L);
        officeDto.setName(TEST_OFFICE_NAME);
        officeDto.setIsActive(true);
        officeDto.setPhone("98544313454");
        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "save", officeDto, String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        Assert.assertTrue(data.get("result").asText().equals("success"));
    }

    @Test
    @Order(2)
    public void getOfficeList() throws IOException {
        OfficeFilter filter = new OfficeFilter();
        filter.setOrgId(1L);
        filter.setName(TEST_OFFICE_NAME);
        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "list", filter, String.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        ListOfficeDto[] officeDtos = objectMapper.treeToValue(data, ListOfficeDto[].class);
        Optional<ListOfficeDto> first = Arrays.stream(officeDtos).
                filter(listOfficeDto -> listOfficeDto.getName().equals(TEST_OFFICE_NAME))
                .findFirst();
        Assert.assertTrue(first.isPresent());
        SingleOfficeDto office = getOfficeDtoById(first.get().getId());
        Assert.assertTrue(office.getName().equals(TEST_OFFICE_NAME));
    }


    @Test
    @Order(3)
    public void getOfficeById() throws JsonProcessingException {
        long id = 1L; //office Id from previous test
        SingleOfficeDto officeDto = getOfficeDtoById(id);
        Assert.assertTrue(officeDto.getName().equals(TEST_OFFICE_NAME));
    }

    @Test
    @Order(4)
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

    public SingleOfficeDto getOfficeDtoById(long id) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(PREFIX + id, String.class);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        SingleOfficeDto officeDto = objectMapper.treeToValue(data, SingleOfficeDto.class);
        return officeDto;
    }

}