package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.service.ReferenceService;
import bellintegrator.com.demo.view.reference.CountryReferenceView;
import bellintegrator.com.demo.view.reference.DocumentReferenceView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReferenceBookController {
    private ReferenceService referenceService;

    @Autowired
    public ReferenceBookController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @PostMapping(path = "/api/docs")
    public DocumentReferenceView getDocumentsList(
            @RequestBody DocumentReferenceView documentView) throws NotFoundException {
        return referenceService.getDocumentView(documentView);
    }

    @PostMapping(path = "/api/countries")
    public CountryReferenceView getCountriesList(
            @RequestBody CountryReferenceView countryView) throws NotFoundException {
        return referenceService.getCountryView(countryView);
    }

}
