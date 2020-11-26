package bellintegrator.com.demo.service;

import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.ListUserDto;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User mapUserSaveDto2User(SaveUserDto saveUserDto) throws Exception;

    User mapUserUpdateDto2User(UpdateUserDto updateUserDto) throws Exception;

    void updateUser(User user) throws Exception;

    List<ListUserDto> findByFilter(UserFilter userFilter);

    SingleUserDto findById(Long id) throws NotFoundException;
}
