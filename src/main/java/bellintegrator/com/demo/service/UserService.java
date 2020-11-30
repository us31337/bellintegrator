package bellintegrator.com.demo.service;

import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.ListUserDto;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    void mapAndSaveUserDto(SaveUserDto saveUserDto) throws Exception;

    void mapAndUpdateUserDto(UpdateUserDto updateUserDto) throws Exception;

    List<ListUserDto> findByFilter(UserFilter userFilter);

    SingleUserDto findById(Long id) throws NotFoundException;
}
