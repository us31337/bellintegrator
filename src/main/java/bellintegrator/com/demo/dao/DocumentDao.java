package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentDao {
    Optional<Document> findById(Long id);
    List<Document> findAll();
    void deleteById(Long id);
    void delete(Document document);
    void add(Document document);
    void update(Document document);

}
