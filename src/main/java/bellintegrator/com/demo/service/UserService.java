package bellintegrator.com.demo.service;

import bellintegrator.com.demo.entity.User;
import com.fasterxml.jackson.databind.JsonNode;

import javax.transaction.Transactional;

public interface UserService {

    Boolean validateJsonNodeForUser(JsonNode node);

    @Transactional
    void saveUserAndDocument(User user);

    User deserializeUserFromJsonString(String jsonString) throws Exception;
}
