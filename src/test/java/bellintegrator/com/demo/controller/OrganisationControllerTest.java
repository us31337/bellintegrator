package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.view.filter.OrganisationFilter;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

class OrganisationControllerTest {

    private final String PREFIX = "http://localhost:8888/api/organisation/";
    private CloseableHttpClient client;
    private Gson gson;

    @BeforeEach
    void setUp() {
        this.client = HttpClients.createDefault();
        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.setPrettyPrinting().create();
    }

    @Test
    void getOrganisationList() throws IOException {
        OrganisationFilter filter = new OrganisationFilter();
        filter.setIsActive(true);
        filter.setName("нева");

        String post = runHttpPost("list", filter);
        System.out.println(post);
    }

    @Test
    void getOrganisationById() throws IOException {
        Long id = 33L;
        HttpGet httpGet = new HttpGet("http://localhost:8888/api/organisation/" + id.toString());
        CloseableHttpResponse response = client.execute(httpGet);
        String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(string);
    }

    @Test
    void saveNewOrganisation() throws IOException {
        SaveOrganisationDto org = new SaveOrganisationDto();
        org.setInn("2370007068");
        org.setAddress("353800, Краснодарский край, ст-ца Полтавская, ул М.Горького, д 5");
        org.setKpp("237001001000");
        org.setName("АО \"ВЕКТОР\"");
        org.setFullName("АКЦИОНЕРНОЕ ОБЩЕСТВО \"ВЕКТОР\"");
        org.setPhone("+74991927500");
        org.setIsActive(true);

        String post = runHttpPost("save", org);
        System.out.println(post);
    }

    @Test
    void updateOrganisation() throws IOException {
        UpdateOrganisationDto orgDto = new UpdateOrganisationDto();
        orgDto.setId(3L);
        orgDto.setName("Тест");
        orgDto.setFullName("Проверочная проверка");
        orgDto.setInn("7825061984");
        orgDto.setKpp("781301001");
        orgDto.setAddress("197101, г Санкт-Петербург, Каменноостровский пр-кт, д 12 литер а");
        orgDto.setIsActive(true);

        String post = runHttpPost("update", orgDto);
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