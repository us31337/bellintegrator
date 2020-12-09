package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.view.filter.UserFilter;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интерфейс для работы с пользователями на уровне базы данных
 *
 * @see User
 */
public interface UserDao {
    /**
     * Поиск пользователя в базе данных по первичному ключу
     * @param id первичный ключ {@link User#id}
     * @return сущность пользователя
     * @throws NotFoundException если в базе данных нет такого пользователя
     */
    User findById(Long id) throws NotFoundException;

    /**
     * Поиск всех пользователей в базе данных
     * @return список всех сущностей пользователей
     * @throws NotFoundException если в базе дыннх нет пользователей
     */
    List<User> findAll() throws NotFoundException;

    /**
     * Удаление пользователя по первчному ключу
     * @param id первичный ключ {@link User#id}
     */
    void deleteById(Long id);

    /**
     * Удаление пользователя
     * @param user сущность пользователя
     */
    void delete(User user);

    /**
     * Сохранение пользователя
     * @param user сущность пользователя
     */
    void add(User user);

    /**
     * Обновление информации о пользователе
     * @param user сущность пользователя
     * @throws Exception если пользователь не найден или не удалось обновить поля
     */
    void update(User user) throws Exception;

    /**
     * Поиск пользователей по фильтру
     * @param filter фильтр
     * @see UserFilter
     * @return список пользователей, соотвествующих условиям фильтра
     */
    List<User> findByFilter(UserFilter filter);
}
