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
    private final String PREFIX = "http://localhost:8888/api/office/";

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
        runHttpPost("list", filter);
    }

    @Test
    void saveNewOffice() throws IOException {
        SaveOfficeDto officeDto = new SaveOfficeDto();
        officeDto.setOrgId(1L);
        officeDto.setName("Аптека2");
        officeDto.setIsActive(true);
        officeDto.setPhone("98544313454");
        String post = runHttpPost("save", officeDto);
        System.out.println(post);
    }

    @Test
    void updateOffice() throws IOException {
        UpdateOfficeDto officeDto = new UpdateOfficeDto();
        officeDto.setId(1L);
        officeDto.setIsActive(false);
        officeDto.setAddress("Невский пр. 1");
        String post = runHttpPost("update", officeDto);
        System.out.println(post);
    }

    private String runHttpPost(String url, Object toJson) throws IOException {
        String json = gson.toJson(toJson);
        StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
        HttpPost httpPost = new HttpPost(PREFIX + url);
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        String answer = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        return answer;
    }
}