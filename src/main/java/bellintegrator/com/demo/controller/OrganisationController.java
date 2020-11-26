package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.service.OrganisationService;
import bellintegrator.com.demo.view.filter.OrganisationFilter;
import bellintegrator.com.demo.view.organisationdto.ListOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;
import javassist.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/organisation")
public class OrganisationController {

    private OrganisationService organisationService;


    @PostMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ListOrganisationDto> getOrganisationList(@RequestBody @Valid OrganisationFilter organisationFilter) {
        return organisationService.findByFilter(organisationFilter);
    }


    @GetMapping("/{id:\\d+}")
    public SingleOrganisationDto getOrganisationById(@PathVariable Long id) throws NotFoundException {
        return organisationService.findById(id);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveNewOrganisation(@RequestBody @Valid SaveOrganisationDto saveOrganisationDto) throws Exception {
        Organisation organisation = organisationService.mapSaveOrganisationDto2Organisation(saveOrganisationDto);
        organisationService.saveOrganisation(organisation);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateOrganisation(@RequestBody @Valid UpdateOrganisationDto updateOrganisationDto) throws Exception {
        Organisation organisation = organisationService.mapUpdateOrganisationDto2Organisation(updateOrganisationDto);
        organisationService.updateOrganisation(organisation);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

}
