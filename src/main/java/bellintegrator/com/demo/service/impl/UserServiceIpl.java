package bellintegrator.com.demo.service.impl;

import bellintegrator.com.demo.dao.*;
import bellintegrator.com.demo.entity.*;
import bellintegrator.com.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceIpl implements UserService {

    private final SimpleDateFormat SDF;
    private String datePattern;
    private OfficeDao officeDao;
    private CountryDao countryDao;
    private DocTypeDao docTypeDao;
    private DocumentDao documentDao;
    private UserDao userDao;

    @Autowired
    public UserServiceIpl(OfficeDao officeDao, CountryDao countryDao,
                          DocTypeDao docTypeDao, DocumentDao documentDao, UserDao userDao,
                          @Value("${date.format.pattern}") String datePattern) {
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.docTypeDao = docTypeDao;
        SDF = new SimpleDateFormat(datePattern);
        this.documentDao = documentDao;
        this.userDao = userDao;
    }

    @Override
    public Boolean validateJsonNodeForUser(JsonNode node) {
        return node.hasNonNull("officeId") &&
                node.hasNonNull("firstName") &&
                node.hasNonNull("position");
    }

    @Override
    @Transactional
    public void saveUserAndDocument(User user) {
//        documentDao.add(user.getDocument());
        userDao.add(user);
    }


    @Override
    public User deserializeUserFromJsonString(String jsonString) throws Exception {
        User user = new User();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonString);
        if (!validateJsonNodeForUser(node)) {
            throw new IllegalArgumentException("Not all required fields presents");
        }
/*
        DocumentType type = new DocumentType();
        Country country = new Country();
*/
        if (node.hasNonNull("officeId")) {
            long officeId = node.get("officeId").asLong();
            Office office = officeDao.findById(officeId);
            user.setOffice(office);
        } else {
            throw new IllegalStateException("Cannot find valid office id");
        }

        DocumentType type = null;
        if (node.hasNonNull("docCode")) {
            type = docTypeDao.findByCode(node.get("docCode").asText());
        } else {
            throw new IllegalStateException("Cannot find valid document code");
        }
        Document document = new Document();
        document.setType(type);
        Date date = null;
        try {
            date = SDF.parse(node.hasNonNull("docDate") ? node.get("docDate").asText() : "null");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        document.setDocDate(date);
        document.setDocNumber(node.hasNonNull("docNumber") ? node.get("docNumber").asText() : null);
        user.setDocument(document);
        document.setUser(user);
        Country country = null;
        if (node.hasNonNull("citizenshipCode")) {
            country = countryDao.findByCode(node.get("citizenshipCode").asInt());
        } else {
            throw new IllegalStateException("Cannot find valid country code");
        }
        user.setCountry(country);
        user.setFirstName(node.hasNonNull("firstName") ? node.get("firstName").asText() : null);
        user.setMiddleName(node.hasNonNull("middleName") ? node.get("middleName").asText() : null);
        user.setLastName(node.hasNonNull("lastName") ? node.get("lastName").asText() : null);
        user.setPosition(node.hasNonNull("position") ? node.get("position").asText() : null);
        user.setPhone(node.hasNonNull("phone") ? node.get("phone").asText() : null);
        user.setIdentified(node.hasNonNull("isIdentified") ? node.get("isIdentified").asBoolean() : true);
        return user;
    }
}
