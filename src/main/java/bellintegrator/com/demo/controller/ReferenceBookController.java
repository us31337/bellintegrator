package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.service.ReferenceService;
import bellintegrator.com.demo.view.reference.CountryReferenceView;
import bellintegrator.com.demo.view.reference.DocumentReferenceView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Класс контроллера для работы со справочниками (страны, виды документов)
 */
@RestController
public class ReferenceBookController {
    private ReferenceService referenceService;

    /**
     * Подключение сервисного слоя
     *
     * @param referenceService
     */
    @Autowired
    public ReferenceBookController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    /**
     * Метод для получения списка видов документов
     * @return лист представлений випов документов
     * @throws NotFoundException в случае если в базе данных нет записей о типах документов
     */
    @GetMapping(path = "/api/docs")
    public List<DocumentReferenceView> getDocumentsList() throws NotFoundException {
        return referenceService.getDocumentsListView();
    }

    /**
     * Метод для получения списка стран
     * @return лист представлений стран
     * @throws NotFoundException если в базе данных нет записей о странах
     */
    @GetMapping(path = "/api/countries")
    public List<CountryReferenceView> getCountriesList() throws NotFoundException {
        return referenceService.getCountriesListView();
    }

}
