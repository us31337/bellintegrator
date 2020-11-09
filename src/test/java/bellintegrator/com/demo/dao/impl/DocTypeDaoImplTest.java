package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.DocTypeDao;
import bellintegrator.com.demo.entity.DocumentType;
import javassist.NotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Disabled
class DocTypeDaoImplTest {

    @Autowired
    private DocTypeDao docTypeDao;

    @Test
    void findById() {
        DocumentType docType = docTypeDao.findById(10L);
        assertTrue(docType.getCode().equals("21"));
    }

    @Test
    void findAll() throws NotFoundException {
        List<DocumentType> docTypeList = docTypeDao.findAll();
        assertTrue(docTypeList.size() > 0);
    }

    @Test
    void add() {
        DocumentType documentType = new DocumentType();
        documentType.setCode("25");
        documentType.setName("Тестовый");
        docTypeDao.add(documentType);
    }

    @Test
    void update() {
        DocumentType documentType = docTypeDao.findById(15L);
        documentType.setName("ТестовыйОбновленный5");
        docTypeDao.update(documentType);
    }

    @Test
    void deleteById() {
        docTypeDao.deleteById(16L);
    }

    @Test
    void delete() {
        DocumentType documentType = docTypeDao.findById(15L);
        docTypeDao.delete(documentType);
    }

}