package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.service.UserService;
import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.ListUserDto;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ListUserDto> getUserList(@RequestBody @Valid UserFilter userFilter) {
        return userService.findByFilter(userFilter);
    }

    @GetMapping("/{id:\\d+}")
    public SingleUserDto getUserById(@PathVariable Long id) throws NotFoundException {
        return userService.findById(id);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveNewUser(@RequestBody @Valid SaveUserDto saveUserDto) throws Exception {
        userService.mapAndSaveUserDto(saveUserDto);
        return Collections.singletonMap("result", "success");
    }

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateUser(@RequestBody @Valid UpdateUserDto updateUserDto) throws Exception {
        userService.mapAndUpdateUserDto(updateUserDto);
        return Collections.singletonMap("result", "success");
    }

}
