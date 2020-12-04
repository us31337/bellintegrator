package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private String PREFIX;
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
    void getUserList() throws IOException {
        UserFilter filter = new UserFilter();
        filter.setOfficeId(1L);

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "list", filter, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        System.out.println(data);
    }

    @Test
    void getUserById() throws IOException {
    }

    @Test
    void saveNewUser() throws ParseException, IOException {
        SaveUserDto userDto = new SaveUserDto();
        userDto.setOfficeId(1L);
        userDto.setFirstName("Andrey");
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
        System.out.println(data);
    }

    @Test
    void updateUser() throws IOException {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setId(1L);
        updateUserDto.setIsIdentified(true);
        updateUserDto.setPosition("кладовщик");
        updateUserDto.setFirstName("Михаил");
        updateUserDto.setMiddleName("Иванович");
        updateUserDto.setDocName("Свидетельство о рождении");

        ResponseEntity<String> response = restTemplate.postForEntity(PREFIX + "update", updateUserDto, String.class);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        JsonNode node = objectMapper.readTree(response.getBody());
        JsonNode data = node.get("body").get("data");
        System.out.println(data);

    }

}