package bellintegrator.com.demo.service;

import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.view.filter.OfficeFilter;
import bellintegrator.com.demo.view.officedto.ListOfficeDto;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;

import java.util.List;

public interface OfficeService {
    List<ListOfficeDto> findByFilter(OfficeFilter officeFilter);

    SingleOrganisationDto findById(Long id);

    Office mapSaveOfficeDto2Offcie(SaveOfficeDto saveOfficeDto);

    void saveOffice(Office office);

    Office mapUpdateOfficeDto2Office(UpdateOfficeDto officeDto);

    void updateOffice(Office office);
}
