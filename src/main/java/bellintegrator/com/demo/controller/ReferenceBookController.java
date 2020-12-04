package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.service.ReferenceService;
import bellintegrator.com.demo.view.reference.CountryReferenceView;
import bellintegrator.com.demo.view.reference.DocumentReferenceView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReferenceBookController {
    private ReferenceService referenceService;

    @Autowired
    public ReferenceBookController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @GetMapping(path = "/api/docs")
    public List<DocumentReferenceView> getDocumentsList() throws NotFoundException {
        return referenceService.getDocumentsListView();
    }

    @GetMapping(path = "/api/countries")
    public List<CountryReferenceView> getCountriesList() throws NotFoundException {
        return referenceService.getCountriesListView();
    }

}
