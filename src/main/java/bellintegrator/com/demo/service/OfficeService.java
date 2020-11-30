package bellintegrator.com.demo.service;

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

    void mapAndSaveOfficeDto(SaveOfficeDto saveOfficeDto) throws NotFoundException;

    void mapAndUpdateOfficeDto(UpdateOfficeDto updateOfficeDto) throws Exception;
}
