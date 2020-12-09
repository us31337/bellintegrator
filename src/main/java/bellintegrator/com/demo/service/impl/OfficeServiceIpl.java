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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceIpl implements OfficeService {

    private OfficeDao officeDao;
    private MapperFacade mapper;
    private OrganisationDao organisationDao;

    /***
     * @param officeDao слой работы с базой данных офсиов
     * @param organisationDao слой работы с базой данных организаций
     * @param mapper маппер для преобразования представлений в сущности
     */
    @Autowired
    public OfficeServiceIpl(OfficeDao officeDao, OrganisationDao organisationDao, MapperFacade mapper) {
        this.officeDao = officeDao;
        this.mapper = mapper;
        this.organisationDao = organisationDao;
    }

    @Override
    public List<ListOfficeDto> findByFilter(OfficeFilter officeFilter) throws Exception {
        List<Office> offices = officeDao.findByFilter(officeFilter);
        List<ListOfficeDto> collect = offices.stream().map(o -> mapper.map(o, ListOfficeDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SingleOfficeDto findById(Long id) throws Exception {
        Office office = officeDao.findById(id);
        SingleOfficeDto officeDto = mapper.map(office, SingleOfficeDto.class);
        return officeDto;
    }

    @Override
    public void mapAndSaveOfficeDto(SaveOfficeDto saveOfficeDto) throws NotFoundException {
        Office office = mapper.map(saveOfficeDto, Office.class);
        Organisation organisation = organisationDao.findById(saveOfficeDto.getOrgId());
        office.setParentOrg(organisation);
        officeDao.add(office);
    }

    @Override
    public void mapAndUpdateOfficeDto(UpdateOfficeDto updateOfficeDto) throws Exception {
        Office office = officeDao.findById(updateOfficeDto.getId());
        mapper.map(updateOfficeDto, office);
        officeDao.update(office);
    }

}
