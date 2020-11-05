package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.DocTypeDao;
import bellintegrator.com.demo.entity.DocumentType;

import java.util.List;
import java.util.Optional;

public class DocTypeDaoImpl implements DocTypeDao {
    @Override
    public Optional<DocumentType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DocumentType> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(DocumentType docType) {

    }

    @Override
    public void add(DocumentType docType) {

    }

    @Override
    public void update(DocumentType docType) {

    }
}
