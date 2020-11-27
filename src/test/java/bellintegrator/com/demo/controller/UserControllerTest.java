package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class UserControllerTest {

    private CloseableHttpClient client;
    private Gson gson;


    @BeforeEach
    void setUp() {
        this.client = HttpClients.createDefault();
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd");
        this.gson = builder.setPrettyPrinting().create();
    }

    @Test
    void getUserList() throws IOException {
        UserFilter filter = new UserFilter();
        filter.setPosition("manager");
        filter.setOfficeId(1L);
        filter.setCitizenshipCode(643);
        String json = gson.toJson(filter);
        StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
        HttpPost httpPost = new HttpPost("http://localhost:8888/api/user/list");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
    }

    @Test
    void getUserById() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8888/api/user/42");
        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
    }

    @Test
    void saveNewUser() throws ParseException, IOException {
        SaveUserDto userDto = new SaveUserDto();
        userDto.setOfficeId(3L);
        userDto.setFirstName("Andrey");
        userDto.setPosition("CEO");
        userDto.setDocCode("21");
        userDto.setDocName("Паспорт гражданина Российской Федерации");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        userDto.setDocNumber("4000123456");
        userDto.setDocDate(sdf.parse("1988-12-22"));
        userDto.setCitizenshipCode(643);
        userDto.setIsIdentified(true);
        StringEntity entity = new StringEntity(gson.toJson(userDto), StandardCharsets.UTF_8);
        HttpPost httpPost = new HttpPost("http://localhost:8888/api/user/save");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
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
        StringEntity entity = new StringEntity(gson.toJson(updateUserDto), StandardCharsets.UTF_8);
        HttpPost httpPost = new HttpPost("http://localhost:8888/api/user/update");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
    }
}