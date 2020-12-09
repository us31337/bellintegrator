package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Country;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интерфейс для работы со странами на уровне базы данных
 *
 * @see Country
 */
public interface CountryDao {
    /**
     * Поиск страны по идентифактору в базе данных
     * @param id идентификатор {@link Country#id}
     * @return сущность страны
     * @throws NotFoundException если id нет в базе
     */
    Country findById(Long id) throws NotFoundException;

    /**
     * Поиск страны в базе данных по названию
     * @param name название страны {@link Country#name}
     * @return сущность страны
     * @throws NotFoundException если такого названия нет в базе
     */
    Country findByName(String name) throws NotFoundException;

    /**
     * Поиск страны в базе данных по ее коду
     * @param code код страны {@link Country#code}
     * @return сущность страны
     * @throws NotFoundException если такого кода нет в базе
     */
    Country findByCode(Integer code) throws NotFoundException;

    /**
     * Возвращает все страны из базы данных
     * @return список стран
     * @throws NotFoundException если база данных не содержит стран
     */
    List<Country> findAll() throws NotFoundException;

    /**
     * Удаление страны из базы данных по ее id
     * @param id первичный ключ страны {@link Country#id}
     */
    void deleteById(Long id);

    /**
     * Удаление страны
     * @param country сущность страны
     */
    void delete(Country country);

    /**
     * Сохранение страны в базу данных
     * @param country сущность страны
     */
    void add(Country country);

    /**
     * Обновление данных страны
     * @param country сущность страны
     * @throws Exception если SQL исключенияе
     */
    void update(Country country) throws Exception;

}
