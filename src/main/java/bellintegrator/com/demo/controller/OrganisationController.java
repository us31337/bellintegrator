package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.service.OrganisationService;
import bellintegrator.com.demo.view.filter.OrganisationFilter;
import bellintegrator.com.demo.view.organisationdto.ListOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Класс контроллера для обработки запросов к организациям
 */
@RestController
@RequestMapping("/api/organisation")
public class OrganisationController {

    /**
     * Подключение сервисного слоя
     */
    @Autowired
    private OrganisationService organisationService;


    /**
     * Метод для получения списка огранизаций
     *
     * @param organisationFilter
     * @return
     */
    @PostMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ListOrganisationDto> getOrganisationList(@RequestBody @Valid OrganisationFilter organisationFilter) {
        return organisationService.findByFilter(organisationFilter);
    }

    /**
     * Метод для получения информации об организации по ее номеру
     * @param id организации
     * @return представление информации об одной организаци
     * @throws NotFoundException если организация с таким id не найдена
     */
    @GetMapping("/{id:\\d+}")
    public SingleOrganisationDto getOrganisationById(@PathVariable Long id) throws NotFoundException {
        return organisationService.findById(id);
    }

    /**
     * Метод для сохранения организации в базе данных
     * @param saveOrganisationDto представление информации об организации
     * @return сообщение об успехе
     * @throws Exception в случае SQL исключений, неверных входных данных
     */
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveNewOrganisation(@RequestBody @Valid SaveOrganisationDto saveOrganisationDto) throws Exception {
        organisationService.mapAndSaveOrganisationDto(saveOrganisationDto);
        return Collections.singletonMap("result", "success");
    }

    /**
     * Метод для обновления даных по организации
     * @param updateOrganisationDto представление информации для обновления
     * @return сообщение об успехе
     * @throws Exception в случае исключений SQL или неверных входных данных
     */
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateOrganisation(@RequestBody @Valid UpdateOrganisationDto updateOrganisationDto) throws Exception {
        organisationService.mapUpdateOrganisationDto(updateOrganisationDto);
        return Collections.singletonMap("result", "success");
    }

}
