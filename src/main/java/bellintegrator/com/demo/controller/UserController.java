package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.dao.UserDao;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.view.UserDto;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private ModelMapper modelMapper;
    private UserDao userDao;

    public UserController(@Autowired ModelMapper modelMapper, UserDao userDao) {
        this.modelMapper = modelMapper;
        this.userDao = userDao;

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

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        User user = null;
        try {
            user = userDao.findById(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        System.out.println(userDto);
        return userDto.getFirstName();
    }
}
