package bellintegrator.com.demo.service;

import bellintegrator.com.demo.view.filter.OrganisationFilter;
import bellintegrator.com.demo.view.organisationdto.ListOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;
import javassist.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface OrganisationService {
    List<ListOrganisationDto> findByFilter(OrganisationFilter organisationFilter);

    SingleOrganisationDto findById(Long id) throws NotFoundException;

    void mapAndSaveOrganisationDto(SaveOrganisationDto saveOrganisationDto) throws SQLException;

    void mapUpdateOrganisationDto(UpdateOrganisationDto updateOrganisationDto) throws Exception;
}
