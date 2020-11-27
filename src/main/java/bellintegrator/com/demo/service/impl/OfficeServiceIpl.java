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
import ma.glasnost.orika.impl.DefaultMapperFactory;
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
    public OfficeServiceIpl(OfficeDao officeDao, OrganisationDao organisationDao) {
        this.officeDao = officeDao;
        this.mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        this.organisationDao = organisationDao;
    }

    @Override
    public List<ListOfficeDto> findByFilter(OfficeFilter officeFilter) throws Exception {
        List<Office> offices = officeDao.findByFilter(officeFilter);
        mapperFactory.classMap(Office.class, ListOfficeDto.class)
                .field("parentOrg.id", "orgId")
                .byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        List<ListOfficeDto> collect = offices.stream().map(o -> mapper.map(o, ListOfficeDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SingleOfficeDto findById(Long id) throws Exception {
        Office office = officeDao.findById(id);
        mapperFactory.classMap(Office.class, SingleOfficeDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SingleOfficeDto officeDto = mapper.map(office, SingleOfficeDto.class);
        return officeDto;
    }

    @Override
    public Office mapSaveOfficeDto2Office(SaveOfficeDto saveOfficeDto) throws NotFoundException {
        mapperFactory.classMap(SaveOfficeDto.class, Office.class)
                .exclude("orgId")
                .byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Office office = mapper.map(saveOfficeDto, Office.class);
        Organisation organisation = organisationDao.findById(saveOfficeDto.getOrgId());
        office.setParentOrg(organisation);
        return office;
    }

    @Override
    public void saveOffice(Office office) {
        System.out.println(office);
        officeDao.add(office);
    }

    @Override
    public Office mapUpdateOfficeDto2Office(UpdateOfficeDto updateOfficeDto) throws Exception {
        Office office = officeDao.findById(updateOfficeDto.getId());
        mapperFactory.classMap(UpdateOfficeDto.class, Office.class)
                .exclude("id")
                .byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(updateOfficeDto, office);
        System.out.println(office);
        return office;
    }

    @Override
    public void updateOffice(Office office) throws Exception {
        officeDao.update(office);
    }
}
