package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.service.OfficeService;
import bellintegrator.com.demo.view.filter.OfficeFilter;
import bellintegrator.com.demo.view.officedto.ListOfficeDto;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.SingleOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;


    @PostMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ListOfficeDto> getOfficeList(@RequestBody @Valid OfficeFilter officeFilter) throws Exception {
        return officeService.findByFilter(officeFilter);
    }


    @GetMapping("/{id:\\d+}")
    public SingleOfficeDto getOfficeById(@PathVariable Long id) throws Exception {
        return officeService.findById(id);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveNewOffice(@RequestBody @Valid SaveOfficeDto saveOfficeDto) throws Exception {
        Office office = officeService.mapSaveOfficeDto2Office(saveOfficeDto);
        officeService.saveOffice(office);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateOffice(@RequestBody @Valid UpdateOfficeDto officeDto) throws Exception {
        Office office = officeService.mapUpdateOfficeDto2Office(officeDto);
        officeService.updateOffice(office);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }


}
