package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
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
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    private String PREFIX;
    private String UNIQ_NAME = "UniqNameForTest";
    private TestRestTemplate restTemplate;
    private ObjectMapper objectMapper;
    @LocalServerPort
    private int port;

    @Autowired
    public UserControllerTest(TestRestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        this.PREFIX = "http://localhost:" + port + "/api/user/";
    }

    @Test
    @Order(1)
    void saveNewUser() throws ParseException, IOException {
        SaveUserDto userDto = new SaveUserDto();
        userDto.setOfficeId(1L);
        userDto.setFirstName(UNIQ_NAME);
        userDto.setPosition("CEO");
        userDto.setDocCode("21");
        userDto.setDocName("Паспорт гражданина Российской Федерации");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        userDto.setDocNumber("4000123456");
        userDto.setDocDate(sdf.parse("1988-12-22"));
        userDto.setCitizenshipCode(643);
        userDto.setIsIdentified(true);

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "save", userDto, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        Assert.assertTrue(data.get("result").asText().equals("success"));
    }


    @Test
    @Order(2)
    void getUserList() throws IOException {
        SingleUserDto[] userDtos = getUserDtoByName(UNIQ_NAME);
        Optional<SingleUserDto> first = Arrays.stream(userDtos)
                .filter(singleUserDto -> singleUserDto.getFirstName().equals(UNIQ_NAME))
                .findFirst();
        Assert.assertTrue(first.get().getFirstName().equals(UNIQ_NAME));
    }

    @Test
    @Order(3)
    void getUserById() throws JsonProcessingException {
        Long id = 1L;
        ResponseEntity<String> response = restTemplate.getForEntity(PREFIX + id.toString(), String.class);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        SingleUserDto dto = objectMapper.treeToValue(data, SingleUserDto.class);
        Assert.assertTrue(dto.getFirstName().equals("Екатерина"));
    }

    private SingleUserDto getUserById(Long id) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(PREFIX + id.toString(), String.class);
        JsonNode data = objectMapper.readTree(response.getBody()).get("body").get("data");
        return objectMapper.treeToValue(data, SingleUserDto.class);
    }

    private SingleUserDto[] getUserDtoByName(String name) throws JsonProcessingException {
        UserFilter filter = new UserFilter();
        filter.setOfficeId(1L);
        filter.setFirstName(name);

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "list", filter, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        SingleUserDto[] userDtos = objectMapper.treeToValue(data, SingleUserDto[].class);
        return userDtos;
    }

    @Test
    @Order(4)
    void updateUser() throws IOException {
        SingleUserDto[] userDtos = getUserDtoByName(UNIQ_NAME);
        String newPosition = "кладовщик";
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setId(userDtos[0].getId());
        updateUserDto.setIsIdentified(true);
        updateUserDto.setPosition(newPosition);
        updateUserDto.setFirstName("Михаил");
        updateUserDto.setMiddleName("Иванович");
        updateUserDto.setDocName("Паспорт гражданина Российской Федерации");

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "update", updateUserDto, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        SingleUserDto user = getUserById(updateUserDto.getId());
        Assert.assertTrue(user.getPosition().equals(newPosition));
    }

}