package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.dao.UserDao;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.filter.UserFilter;
import bellintegrator.com.demo.view.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private ModelMapper modelMapper;
    private UserDao userDao;
    private ObjectMapper jsonMapper;

    public UserController(@Autowired ModelMapper modelMapper,
                          UserDao userDao, ObjectMapper jsonMapper) {
        this.modelMapper = modelMapper;
        this.userDao = userDao;
        this.jsonMapper = jsonMapper;

        this.modelMapper.typeMap(User.class, UserDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getDocument().getType().getName(),
                    UserDto::setDocName);
            mapper.map(src -> src.getDocument().getDocNumber(),
                    UserDto::setDocNumber);
            mapper.map(src -> src.getDocument().getDocDate(),
                    UserDto::setDocDate);
            mapper.map(src -> src.getCountry().getName(),
                    UserDto::setCitizenshipName);
            mapper.map(src -> src.getCountry().getCode(),
                    UserDto::setCitizenshipCode);
        });

    }

    @PostMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUserList(@RequestBody String requestBody) throws JsonProcessingException {
        System.out.println(requestBody);
        UserFilter filter = null;
        try {
            filter = jsonMapper.readValue(requestBody, UserFilter.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Cannot deserialize user filter from request " + requestBody);
            throw e;
        }
        LOGGER.info("Returning user list with applied filter");
        List<User> userList = userDao.findByFilter(filter);
        List<UserDto> collect = userList.stream().map(u -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
        return collect;
    }


    @GetMapping("/{id:\\d+}")
    public UserDto getUserById(@PathVariable Long id) throws NotFoundException {
        User user = null;
        try {
            user = userDao.findById(id);
        } catch (Exception e) {
            LOGGER.error("Exception from UserDao " + e.getMessage());
            throw e;
        }
        LOGGER.info("Returning user by Id");
        return modelMapper.map(user, UserDto.class);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveNewUser(@RequestBody String requestBody) {

    }

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody String requestBody) {

    }


}
