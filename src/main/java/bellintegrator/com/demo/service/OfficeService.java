package bellintegrator.com.demo.service;

import bellintegrator.com.demo.view.filter.OfficeFilter;
import bellintegrator.com.demo.view.officedto.ListOfficeDto;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.SingleOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интерфейс для работы с офисами
 *
 * @see bellintegrator.com.demo.entity.Office
 */
public interface OfficeService {
    /**
     * Поиск списка офиса, соответсвующих условия фильтра
     * @param officeFilter фильтр для офисов {@link OfficeFilter}
     * @return список представления офисов, соответсвующих условия фильтра
     * @see ListOfficeDto
     * @throws Exception в случае SQL исключения
     */
    List<ListOfficeDto> findByFilter(OfficeFilter officeFilter) throws Exception;

    /**
     * Поиск офиса по первичному ключу
     * @param id первичный ключ
     * {@link bellintegrator.com.demo.entity.Office#officeId}
     * @return представление отдельного офиса
     * @see SingleOfficeDto
     * @throws Exception в случае SQL исключения
     */
    SingleOfficeDto findById(Long id) throws Exception;

    /**
     * Преобразование представления и сохранение офиса
     * @param saveOfficeDto {@link SaveOfficeDto}
     * @throws NotFoundException
     */
    void mapAndSaveOfficeDto(SaveOfficeDto saveOfficeDto) throws NotFoundException;

    /**
     * Преобразование представления и обновление данных об офисе
     * @param updateOfficeDto {@link UpdateOfficeDto}
     * @throws Exception
     */
    void mapAndUpdateOfficeDto(UpdateOfficeDto updateOfficeDto) throws Exception;
}
