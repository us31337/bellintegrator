package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Document;
import javassist.NotFoundException;

import java.util.List;

/**
 * Интерфейс для работы с документами на уровне базы данных
 *
 * @see Document
 */
public interface DocumentDao {
    /**
     * Поиск документа в базе данных по превичному ключу
     * @param id первичный ключ {@link Document#id}
     * @return сущность документа
     * @throws NotFoundException если ничего не найдено
     */
    Document findById(Long id) throws NotFoundException;

    /**
     * Возврат всех документов в базе данных
     * @return списко всех документов
     * @throws NotFoundException если документов в базе данных не найдено
     */
    List<Document> findAll() throws NotFoundException;

    /**
     * Удаление документа по первичному ключу
     * @param id первичный ключ {@link Document#id}
     */
    void deleteById(Long id);

    /**
     * Удаление документа
     * @param document сущность документа
     */
    void delete(Document document);

    /**
     * Сохранение документа в базе данных
     * @param document сущность документа
     */
    void add(Document document);

    /**
     * Обновление документа в базе данных
     * @param document сущность документа
     * @throws Exception в случае SQL исключения
     */
    void update(Document document) throws Exception;
}
