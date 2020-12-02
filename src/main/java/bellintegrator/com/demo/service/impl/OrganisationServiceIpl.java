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
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganisationServiceIpl implements OrganisationService {

    private OrganisationDao organisationDao;
    private MapperFactory mapperFactory;

    @Autowired
    public OrganisationServiceIpl(OrganisationDao organisationDao, MapperFactory mapperFactory) {
        this.organisationDao = organisationDao;
        this.mapperFactory = mapperFactory;
    }

    @Override
    public List<ListOrganisationDto> findByFilter(OrganisationFilter organisationFilter) {
        List<Organisation> organisationList = organisationDao.findByFilter(organisationFilter);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        List<ListOrganisationDto> collect = organisationList.stream().map(o -> mapper.map(o, ListOrganisationDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SingleOrganisationDto findById(Long id) throws NotFoundException {
        Organisation organisation = organisationDao.findById(id);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SingleOrganisationDto organisationDto = mapper.map(organisation, SingleOrganisationDto.class);
        return organisationDto;
    }

    @Override
    public void mapAndSaveOrganisationDto(SaveOrganisationDto saveOrganisationDto) {
        Organisation organisation = new Organisation();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(saveOrganisationDto, organisation);
        organisationDao.add(organisation);
    }

    @Override
    public void mapUpdateOrganisationDto(UpdateOrganisationDto updateOrganisationDto) throws Exception {
        Organisation organisation = organisationDao.findById(updateOrganisationDto.getId());
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(updateOrganisationDto, organisation);
        organisationDao.update(organisation);
    }
}
