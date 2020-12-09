package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.service.UserService;
import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.ListUserDto;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import javassist.NotFoundException;
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

    /**
     * Подключение сервисного слоя
     */
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Для получения списка сущетвующих пользователей
     *
     * @param userFilter представление фильтра по пользователям
     * @return списко представлений пользователей
     */
    @PostMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ListUserDto> getUserList(@RequestBody @Valid UserFilter userFilter) {
        return userService.findByFilter(userFilter);
    }

    /**
     * Полученияе информации по конкретному пользвоателю
     * @param id пользователя
     * @return представление информации о пользователе
     * @throws NotFoundException если пользвоателя нет в базе данных
     */
    @GetMapping("/{id:\\d+}")
    public SingleUserDto getUserById(@PathVariable Long id) throws NotFoundException {
        return userService.findById(id);
    }

    /**
     * Сохранение пользователя в базе данных
     * @param saveUserDto представление информации о сохраняемом пользователе
     * @return сообзение об успехе
     * @throws Exception в случае SQL исключений, неверных входных данных
     */
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveNewUser(@RequestBody @Valid SaveUserDto saveUserDto) throws Exception {
        userService.mapAndSaveUserDto(saveUserDto);
        return Collections.singletonMap("result", "success");
    }

    /**
     * Обновление данных о пользователе
     * @param updateUserDto представление обновляемой информации
     * @return сообщение об успехе
     * @throws Exception в случае SQL исключений, неверных входных данных
     */
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateUser(@RequestBody @Valid UpdateUserDto updateUserDto) throws Exception {
        userService.mapAndUpdateUserDto(updateUserDto);
        return Collections.singletonMap("result", "success");
    }

}
