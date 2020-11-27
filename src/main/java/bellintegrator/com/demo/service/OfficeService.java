package bellintegrator.com.demo.service;

import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.view.filter.OfficeFilter;
import bellintegrator.com.demo.view.officedto.ListOfficeDto;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.SingleOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
import javassist.NotFoundException;

import java.util.List;

public interface OfficeService {
    List<ListOfficeDto> findByFilter(OfficeFilter officeFilter) throws Exception;

    SingleOfficeDto findById(Long id) throws Exception;

    Office mapSaveOfficeDto2Office(SaveOfficeDto saveOfficeDto) throws NotFoundException;

    void saveOffice(Office office);

    Office mapUpdateOfficeDto2Office(UpdateOfficeDto updateOfficeDto) throws Exception;

    void updateOffice(Office office) throws Exception;
}
