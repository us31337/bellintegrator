package bellintegrator.com.demo.service;

import bellintegrator.com.demo.view.reference.CountryReferenceView;
import bellintegrator.com.demo.view.reference.DocumentReferenceView;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интерфейс для работы со справочниками
 */

public interface ReferenceService {
    /**
     * Получение списка всех типов документов
     *
     * @return {@link DocumentReferenceView}
     * @throws NotFoundException если в базе данных нет типов документов
     */
    List<DocumentReferenceView> getDocumentsListView() throws NotFoundException;

    /**
     * Получение списка всех стран
     * @return {@link CountryReferenceView}
     * @throws NotFoundException если в базе данных нет стран
     */
    List<CountryReferenceView> getCountriesListView() throws NotFoundException;
}
