package bellintegrator.com.demo.service;

import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.view.filter.OrganisationFilter;
import bellintegrator.com.demo.view.organisationdto.ListOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;

import java.util.List;

public interface OrganisationService {
    List<ListOrganisationDto> findByFilter(OrganisationFilter organisationFilter);

    SingleOrganisationDto findById(Long id);

    Organisation mapSaveOrganisationDto2Organisation(SaveOrganisationDto saveOrganisationDto);

    void saveOrganisation(Organisation organisation);

    Organisation mapUpdateOrganisationDto2Organisation(UpdateOrganisationDto updateOrganisationDto);

    void updateOrganisation(Organisation organisation);
}
