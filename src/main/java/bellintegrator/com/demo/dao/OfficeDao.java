package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.view.filter.OfficeFilter;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интерфейс для работы с офсами на уровне базы данных
 *
 * @see Office
 */
public interface OfficeDao {
    /**
     * Поиск офиса в базе данных по первичному ключу
     * @param id первичный ключ {@link Office#officeId}
     * @return сущность офиса
     * @throws Exception в случае SQL исключения
     */
    Office findById(Long id) throws Exception;

    /**
     * Возврат всех офисов из базы данных
     * @return списко всех офисов
     * @throws NotFoundException если в базе дынных нет офисов
     */
    List<Office> findAll() throws NotFoundException;

    /**
     * Удаление офиса по первичному ключу
     * @param id первичный ключ {@link Office#officeId}
     */
    void deleteById(Long id);

    /**
     * Удаление офиса
     * @param office сущность офиса
     */
    void delete(Office office);

    /**
     * Сохранение офиса в базе данных
     * @param office сущность офиса
     */
    void add(Office office);

    /**
     * Обновление информации об офисе в базе данных
     * @param office сущность офиса
     * @throws Exception в случае SQL исключения
     */
    void update(Office office) throws Exception;

    /**
     * Поиск в базе данных списка офиса, соответсвующих условия фильтра
     * @param filter фильтр для офисов
     * @see OfficeFilter
     * @return список офисов, соответсвующих условия фильтра
     * @throws Exception в случае SQL исключения
     */
    List<Office> findByFilter(OfficeFilter filter) throws Exception;

}
