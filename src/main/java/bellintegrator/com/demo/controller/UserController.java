package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.filter.UserFilter;
import bellintegrator.com.demo.service.UserService;
import bellintegrator.com.demo.view.SingleUserDto;
import bellintegrator.com.demo.view.UpdateUserDto;
import bellintegrator.com.demo.view.UserListDto;
import bellintegrator.com.demo.view.UserSaveDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<UserListDto> getUserList(@RequestBody UserFilter userFilter) throws JsonProcessingException {
        LOGGER.info("Returning user list with applied filter");
        List<User> userList = userService.findByFilter(userFilter);
        ModelMapper modelMapper = new ModelMapper();
        List<UserListDto> collect = userList.stream().map(u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return collect;
    }


    @GetMapping("/{id:\\d+}")
    public SingleUserDto getUserById(@PathVariable Long id) throws NotFoundException {
        User user = null;
        try {
            user = userService.findById(id);
        } catch (Exception e) {
            LOGGER.error("Exception from UserDao " + e.getMessage());
            throw e;
        }
        LOGGER.info("Returning user by Id");
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(User.class, SingleUserDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getDocument().getType().getName(),
                    SingleUserDto::setDocName);
            mapper.map(src -> src.getDocument().getDocNumber(),
                    SingleUserDto::setDocNumber);
            mapper.map(src -> src.getDocument().getDocDate(),
                    SingleUserDto::setDocDate);
            mapper.map(src -> src.getCountry().getName(),
                    SingleUserDto::setCitizenshipName);
            mapper.map(src -> src.getCountry().getCode(),
                    SingleUserDto::setCitizenshipCode);
        });
        return modelMapper.map(user, SingleUserDto.class);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveNewUser(@RequestBody UserSaveDto userSaveDto) throws Exception {
        if (userService.validateSaveUserDto(userSaveDto)) {
            User user = userService.mapUserSaveDto2User(userSaveDto);
            userService.saveUser(user);
        } else {
            throw new IllegalArgumentException("Missing some required fields");
        }
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateUser(@RequestBody UpdateUserDto updateUserDto) throws Exception {
        if (userService.validateUpdateUserDto(updateUserDto)) {
            User user = userService.mapUserUpdateDto2User(updateUserDto);
            userService.updateUser(user);
        } else {
            throw new IllegalArgumentException("Missing some required fields");
        }
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }


}
