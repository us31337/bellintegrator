package bellintegrator.com.demo.service;

import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.ListUserDto;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интефейс сервсиного слоя для работы с пользователями
 *
 * @see bellintegrator.com.demo.entity.User
 */
public interface UserService {

    /**
     * Преобразование и сохранение представления информации о пользователи
     * @param saveUserDto {@link SaveUserDto}
     * @throws Exception
     */
    void mapAndSaveUserDto(SaveUserDto saveUserDto) throws Exception;

    /**
     * Преобразование и обновление представления информации о пользователи
     * @param updateUserDto {@link bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto}
     * @throws Exception
     */
    void mapAndUpdateUserDto(UpdateUserDto updateUserDto) throws Exception;

    /**
     * Поиск всех орагнизаций, попадающих под фильтр
     * @param userFilter {@link UserFilter}
     * @return список представлений пользователей {@link ListUserDto}
     */
    List<ListUserDto> findByFilter(UserFilter userFilter);

    /**
     * Получение информации в виде представления о пользователе по его id
     * @param id {@link bellintegrator.com.demo.entity.User#id}
     * @return {@link SingleUserDto}
     * @throws NotFoundException
     */
    SingleUserDto findById(Long id) throws NotFoundException;
}
