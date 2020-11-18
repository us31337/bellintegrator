package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.DocumentType;
import javassist.NotFoundException;

import java.util.List;

public interface DocTypeDao {
    DocumentType findById(Long id) throws NotFoundException;
    List<DocumentType> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(DocumentType docType);
    void add(DocumentType docType);
    void update(DocumentType docType) throws NotFoundException;
}
