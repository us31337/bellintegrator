package bellintegrator.com.demo.controller;

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
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Класс контроллера для обработки запросов к офисам
 */
@RestController
@RequestMapping("/api/office")
public class OfficeController {

    /**
     * Подключение сервисного слоя
     */
    @Autowired
    private OfficeService officeService;

    /**
     * Метод для выдачи списка офисов в ответ на фильтр
     *
     * @param officeFilter - фильтр по параметрам офисов
     * @return списко офисов
     * @throws Exception если не удалось применить фильтр
     */
    @PostMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ListOfficeDto> getOfficeList(@RequestBody @Valid OfficeFilter officeFilter) throws Exception {
        return officeService.findByFilter(officeFilter);
    }

    /**
     * Метод выдает информацию по одному офису, получая его id
     * @param id идентификатор офиса
     * @return представление одного офиса
     * @throws Exception в случае, если офис не найден
     */
    @GetMapping("/{id:\\d+}")
    public SingleOfficeDto getOfficeById(@PathVariable Long id) throws Exception {
        return officeService.findById(id);
    }

    /**
     * Метод сохраняет данные об офисе
     * @param saveOfficeDto представление информации об офисе
     * @return сообщение об успехе
     * @throws Exception в случае неверной информации или SQL ошибки
     */
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveNewOffice(@RequestBody @Valid SaveOfficeDto saveOfficeDto) throws Exception {
        officeService.mapAndSaveOfficeDto(saveOfficeDto);
        return Collections.singletonMap("result", "success");
    }

    /**
     * Метод обновляет информацие об уже существующем офисе
     * @param officeDto представление обновляемой информации
     * @return сообщение об успехе
     * @throws Exception в случае неверной информации или SQL ошибки
     */
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateOffice(@RequestBody @Valid UpdateOfficeDto officeDto) throws Exception {
        officeService.mapAndUpdateOfficeDto(officeDto);
        return Collections.singletonMap("result", "success");
    }


}
