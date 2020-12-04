package bellintegrator.com.demo.service;

import bellintegrator.com.demo.view.reference.CountryReferenceView;
import bellintegrator.com.demo.view.reference.DocumentReferenceView;
import javassist.NotFoundException;

import java.util.List;

public interface ReferenceService {
    List<DocumentReferenceView> getDocumentsListView() throws NotFoundException;

    List<CountryReferenceView> getCountriesListView() throws NotFoundException;
}
