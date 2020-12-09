package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.DocumentType;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интерфейс для работы с типами документов на уровне базы данных
 *
 * @see DocumentType
 */
public interface DocTypeDao {
    /**
     * Поиск типа документа по первичному ключу в базе данных
     * @param id первичный ключ {@link DocumentType#id}
     * @return сущность типа документа
     * @throws NotFoundException если тип документа не найден в базе данных
     */
    DocumentType findById(Long id) throws NotFoundException;

    /**
     * Поиск типа документа по наименованию в базе данных
     * @param name наименование документа {@link DocumentType#name}
     * @return сущность типа документа
     * @throws NotFoundException если тип документа не найден в базе данных
     */
    DocumentType findByName(String name) throws NotFoundException;

    /**
     * Поиск типа документа по коду в базе данных
     * @param code код документа {@link DocumentType#code}
     * @return сущность типа документа
     * @throws NotFoundException если тип документа не найден в базе данных
     */
    DocumentType findByCode(String code) throws NotFoundException;

    /**
     * Возврат списка всех документов в базе данных
     * @return список типов документов
     * @throws NotFoundException если в базе даннхы нет типов документов
     */
    List<DocumentType> findAll() throws NotFoundException;

    /**
     * Удаление типа документа по первичному ключу в базе данных
     * @param id первичный ключ {@link DocumentType#id}
     */
    void deleteById(Long id);

    /**
     * Удаление типа документа
     * @param docType сущность типа документа
     */
    void delete(DocumentType docType);

    /**
     * Сохранение в базе данных сущности типа документа
     * @param docType сущность типа документа
     */
    void add(DocumentType docType);

    /**
     * Обновление информации о типе документа
     * @param docType сущность типа документа
     * @throws Exception в случае SQL исключений
     */
    void update(DocumentType docType) throws Exception;
}
