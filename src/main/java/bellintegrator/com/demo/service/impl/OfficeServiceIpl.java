package bellintegrator.com.demo.service.impl;

import bellintegrator.com.demo.dao.OfficeDao;
import bellintegrator.com.demo.dao.OrganisationDao;
import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.service.OfficeService;
import bellintegrator.com.demo.view.filter.OfficeFilter;
import bellintegrator.com.demo.view.officedto.ListOfficeDto;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.SingleOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
import javassist.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceIpl implements OfficeService {

    private OfficeDao officeDao;
    private MapperFactory mapperFactory;
    private OrganisationDao organisationDao;

    @Autowired
    public OfficeServiceIpl(OfficeDao officeDao, OrganisationDao organisationDao, MapperFactory mapperFactory) {
        this.officeDao = officeDao;
        this.mapperFactory = mapperFactory;
        this.organisationDao = organisationDao;
    }

    @Override
    public List<ListOfficeDto> findByFilter(OfficeFilter officeFilter) throws Exception {
        List<Office> offices = officeDao.findByFilter(officeFilter);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        List<ListOfficeDto> collect = offices.stream().map(o -> mapper.map(o, ListOfficeDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SingleOfficeDto findById(Long id) throws Exception {
        Office office = officeDao.findById(id);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SingleOfficeDto officeDto = mapper.map(office, SingleOfficeDto.class);
        return officeDto;
    }

    @Override
    public void mapAndSaveOfficeDto(SaveOfficeDto saveOfficeDto) throws NotFoundException {
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Office office = mapper.map(saveOfficeDto, Office.class);
        Organisation organisation = organisationDao.findById(saveOfficeDto.getOrgId());
        office.setParentOrg(organisation);
        officeDao.add(office);
    }

    @Override
    public void mapAndUpdateOfficeDto(UpdateOfficeDto updateOfficeDto) throws Exception {
        Office office = officeDao.findById(updateOfficeDto.getId());
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(updateOfficeDto, office);
        officeDao.update(office);
    }

}
