package bellintegrator.com.demo.service;

import bellintegrator.com.demo.view.filter.OrganisationFilter;
import bellintegrator.com.demo.view.organisationdto.ListOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;
import javassist.NotFoundException;

import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс для работы с организациями
 *
 * @see bellintegrator.com.demo.entity.Organisation
 */
public interface OrganisationService {
    /**
     * Возвращает список всех офисов соотвествующих условиям фильтра
     * @param organisationFilter {@link OrganisationFilter}
     * @return список всех офисов соотвествующих условиям фильтра
     */
    List<ListOrganisationDto> findByFilter(OrganisationFilter organisationFilter);

    /**
     * Поиск организацим по идентификатору
     * @param id первичный ключ в базе данных {@link bellintegrator.com.demo.entity.Organisation#id}
     * @return представление информации об организации {@link SingleOrganisationDto}
     * @throws NotFoundException
     */
    SingleOrganisationDto findById(Long id) throws NotFoundException;

    /**
     * Преобразование и сохранение представления информации об организации
     * @param saveOrganisationDto {@link SaveOrganisationDto}
     * @throws SQLException если сохранение не удалось
     */
    void mapAndSaveOrganisationDto(SaveOrganisationDto saveOrganisationDto) throws SQLException;

    /**
     * Преобразование и обновление представления информации об организации
     * @param updateOrganisationDto {@link UpdateOrganisationDto}
     * @throws Exception
     */
    void mapUpdateOrganisationDto(UpdateOrganisationDto updateOrganisationDto) throws Exception;
}
