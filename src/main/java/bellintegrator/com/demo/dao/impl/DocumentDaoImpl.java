package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.DocumentDao;
import bellintegrator.com.demo.entity.Document;

import java.util.List;
import java.util.Optional;

public class DocumentDaoImpl implements DocumentDao {
    @Override
    public Optional<Document> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Document> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Document document) {

    }

    @Override
    public void add(Document document) {

    }

    @Override
    public void update(Document document) {

    }
}
