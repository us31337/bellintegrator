package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.view.filter.OrganisationFilter;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интерфейс для работы с организациями на уровне базы данных
 *
 * @see Organisation
 */
public interface OrganisationDao {
    /**
     * Поиск организации в базе данных по первичному ключу
     * @param id первичный ключ {@link Organisation#id}
     * @return сущность организации
     * @throws NotFoundException если ничего не найдено
     */
    Organisation findById(Long id) throws NotFoundException;

    /**
     * Поиск всех организаций в базе данных
     * @return списко всех организаций
     * @throws NotFoundException если в базе данных нет организаций
     */
    List<Organisation> findAll() throws NotFoundException;

    /**
     * Удаление организации по превичному ключу
     * @param id первичный ключ {@link Organisation#id}
     */
    void deleteById(Long id);

    /**
     * Удаление орагнизации
     * @param organisation сущность организации
     */
    void delete(Organisation organisation);

    /**
     * Сохранение орагнизации в базе данных
     * @param organisation сущность организации
     */
    void add(Organisation organisation);

    /**
     * Обновление данных об организации
     * @param organisation сущность организации
     * @throws NotFoundException если организация не найдена по первичному ключу
     * @throws IllegalAccessException если не удалось обновить необходимые поля
     */
    void update(Organisation organisation) throws NotFoundException, IllegalAccessException;

    /**
     * Поиск в базе данных списка офиса, соответсвующих условия фильтра
     * @param filter фильтр для офисов
     * @see OrganisationFilter
     * @return список офисов, соответсвующих условия фильтра
     */
    List<Organisation> findByFilter(OrganisationFilter filter);

}
