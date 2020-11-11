package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Document;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface DocumentDao {
    Document findById(Long id) throws NotFoundException;
    List<Document> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(Document document);
    void add(Document document);
    void update(Document document);
}
