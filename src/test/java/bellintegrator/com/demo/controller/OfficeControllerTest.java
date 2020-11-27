package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.filter.OfficeFilter;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
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

class OfficeControllerTest {

    private CloseableHttpClient client;
    private Gson gson;

    @BeforeEach
    void setUp() {
        this.client = HttpClients.createDefault();
        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.setPrettyPrinting().create();
    }

    @Test
    void getOfficeById() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8888/api/office/1");
        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
    }

    @Test
    void getOfficeList() throws IOException {
        OfficeFilter filter = new OfficeFilter();
        filter.setOrgId(1L);
        filter.setName("аптека");
        String json = gson.toJson(filter);
        StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
        HttpPost httpPost = new HttpPost("http://localhost:8888/api/office/list");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
    }

    @Test
    void saveNewOffice() throws IOException {
        SaveOfficeDto officeDto = new SaveOfficeDto();
        officeDto.setOrgId(1L);
        officeDto.setName("Аптека");
        officeDto.setIsActive(true);
        officeDto.setPhone("98544313454");
        StringEntity entity = new StringEntity(gson.toJson(officeDto), StandardCharsets.UTF_8);
        HttpPost httpPost = new HttpPost("http://localhost:8888/api/office/save");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
    }

    @Test
    void updateOffice() throws IOException {
        UpdateOfficeDto officeDto = new UpdateOfficeDto();
        officeDto.setId(1L);
        officeDto.setIsActive(false);
        officeDto.setAddress("Невский пр. 1");
        StringEntity entity = new StringEntity(gson.toJson(officeDto), StandardCharsets.UTF_8);
        HttpPost httpPost = new HttpPost("http://localhost:8888/api/office/update");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
    }
}