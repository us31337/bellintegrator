package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Country;
import bellintegrator.com.demo.entity.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocTypeDao {
    Optional<DocumentType> findById(Long id);
    List<DocumentType> findAll();
    void deleteById(Long id);
    void delete(DocumentType docType);
    void add(DocumentType docType);
    void update(DocumentType docType);
}
