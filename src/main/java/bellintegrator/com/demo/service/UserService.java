package bellintegrator.com.demo.service;

import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.filter.UserFilter;
import bellintegrator.com.demo.view.UpdateUserDto;
import bellintegrator.com.demo.view.UserSaveDto;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    boolean validateSaveUserDto(UserSaveDto userSaveDto);

    User mapUserSaveDto2User(UserSaveDto userSaveDto) throws Exception;

    boolean validateUpdateUserDto(UpdateUserDto updateUserDto);

    User mapUserUpdateDto2User(UpdateUserDto updateUserDto) throws Exception;

    void updateUser(User user) throws Exception;

    List<User> findByFilter(UserFilter userFilter);

    User findById(Long id) throws NotFoundException;
}
