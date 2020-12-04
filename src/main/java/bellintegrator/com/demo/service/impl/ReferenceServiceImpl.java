package bellintegrator.com.demo.service.impl;

import bellintegrator.com.demo.dao.CountryDao;
import bellintegrator.com.demo.dao.DocTypeDao;
import bellintegrator.com.demo.entity.Country;
import bellintegrator.com.demo.entity.DocumentType;
import bellintegrator.com.demo.service.ReferenceService;
import bellintegrator.com.demo.view.reference.CountryReferenceView;
import bellintegrator.com.demo.view.reference.DocumentReferenceView;
import javassist.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceServiceImpl implements ReferenceService {
    private DocTypeDao documentDao;
    private CountryDao countryDao;
    private MapperFacade mapper;

    @Autowired
    public ReferenceServiceImpl(DocTypeDao documentDao,
                                MapperFacade mapper, CountryDao countryDao) {
        this.documentDao = documentDao;
        this.countryDao = countryDao;
        this.mapper = mapper;
    }

    @Override
    public DocumentReferenceView getDocumentView(DocumentReferenceView documentView) throws NotFoundException {
        DocumentType documentType = documentDao.findByCode(documentView.getCode());
        mapper.map(documentType, documentView);
        return documentView;
    }

    @Override
    public CountryReferenceView getCountryView(CountryReferenceView countryView) throws NotFoundException {
        Country country = countryDao.findByCode(countryView.getCode());
        mapper.map(country, countryView);
        return countryView;
    }
}
