package bellintegrator.com.demo.service.impl;

import bellintegrator.com.demo.dao.OrganisationDao;
import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.service.OrganisationService;
import bellintegrator.com.demo.view.filter.OrganisationFilter;
import bellintegrator.com.demo.view.organisationdto.ListOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;
import javassist.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OrganisationServiceIpl implements OrganisationService {

    private OrganisationDao organisationDao;
    private MapperFacade mapper;

    /**
     * @param organisationDao слой работы с базой данных
     * @param mapper          преобразователь представлений в сущности
     */
    @Autowired
    public OrganisationServiceIpl(OrganisationDao organisationDao, MapperFacade mapper) {
        this.organisationDao = organisationDao;
        this.mapper = mapper;
    }

    @Override
    public List<ListOrganisationDto> findByFilter(OrganisationFilter organisationFilter) {
        List<Organisation> organisationList = organisationDao.findByFilter(organisationFilter);
        List<ListOrganisationDto> collect = organisationList.stream().map(o -> mapper.map(o, ListOrganisationDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SingleOrganisationDto findById(Long id) throws NotFoundException {
        Organisation organisation = organisationDao.findById(id);
        SingleOrganisationDto organisationDto = mapper.map(organisation, SingleOrganisationDto.class);
        return organisationDto;
    }

    @Override
    public void mapAndSaveOrganisationDto(SaveOrganisationDto saveOrganisationDto) {
        Organisation organisation = new Organisation();
        mapper.map(saveOrganisationDto, organisation);
        organisationDao.add(organisation);
    }

    @Override
    public void mapUpdateOrganisationDto(UpdateOrganisationDto updateOrganisationDto) throws Exception {
        Organisation organisation = organisationDao.findById(updateOrganisationDto.getId());
        mapper.map(updateOrganisationDto, organisation);
        organisationDao.update(organisation);
    }
}
