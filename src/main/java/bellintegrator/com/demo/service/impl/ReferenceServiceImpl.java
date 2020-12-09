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

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class ReferenceServiceImpl implements ReferenceService {
    private DocTypeDao documentDao;
    private CountryDao countryDao;
    private MapperFacade mapper;

    /**
     * @param documentDao слой работы с базой данных документов
     * @param mapper      преобразователь представлений в сущности
     * @param countryDao  слой работы с базой данных стран
     */
    @Autowired
    public ReferenceServiceImpl(DocTypeDao documentDao,
                                MapperFacade mapper, CountryDao countryDao) {
        this.documentDao = documentDao;
        this.countryDao = countryDao;
        this.mapper = mapper;
    }

    @Override
    public List<DocumentReferenceView> getDocumentsListView() throws NotFoundException {
        List<DocumentType> types = documentDao.findAll();
        List<DocumentReferenceView> views = types.stream()
                .map(t -> mapper.map(t, DocumentReferenceView.class))
                .collect(Collectors.toList());
        return views;
    }

    @Override
    public List<CountryReferenceView> getCountriesListView() throws NotFoundException {
        List<Country> countries = countryDao.findAll();
        List<CountryReferenceView> viewList = countries.stream()
                .map(c -> mapper.map(c, CountryReferenceView.class))
                .collect(Collectors.toList());
        return viewList;
    }
}
