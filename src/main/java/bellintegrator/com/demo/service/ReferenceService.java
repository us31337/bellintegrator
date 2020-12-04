package bellintegrator.com.demo.service;

import bellintegrator.com.demo.view.reference.CountryReferenceView;
import bellintegrator.com.demo.view.reference.DocumentReferenceView;
import javassist.NotFoundException;

public interface ReferenceService {
    DocumentReferenceView getDocumentView(DocumentReferenceView documentView) throws NotFoundException;

    CountryReferenceView getCountryView(CountryReferenceView countryView) throws NotFoundException;
}
