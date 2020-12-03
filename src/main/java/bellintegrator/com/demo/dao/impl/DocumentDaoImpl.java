package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.DocumentDao;
import bellintegrator.com.demo.entity.Document;
import bellintegrator.com.demo.service.NotNullFieldsEntityCopier;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DocumentDaoImpl implements DocumentDao {
    @PersistenceContext
    private EntityManager em;
    private NotNullFieldsEntityCopier<Document> notNullFieldsEntityCopier;

    @Autowired
    public DocumentDaoImpl(NotNullFieldsEntityCopier<Document> notNullFieldsEntityCopier) {
        this.notNullFieldsEntityCopier = notNullFieldsEntityCopier;
    }

    @Override
    public Document findById(Long id) throws NotFoundException {
        Document result =  em.find(Document.class, id);
        if (result != null) {
            return result;
        } else {
            throw new NotFoundException("Document with id " + id + " not found!");
        }
    }

    @Override
    public List<Document> findAll() throws NotFoundException {
        Query query = em.createQuery("select d from Document d");
        List<Document> list = query.getResultList();
        if (list.size() > 0) {
            return list;
        } else {
            throw new NotFoundException("No elements in table documents");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Document> cd = cb.createCriteriaDelete(Document.class);
        Root<Document> documentRoot = cd.from(Document.class);

        Predicate idPredicate = cb.equal(documentRoot.get("id"), id);
        em.createQuery(cd.where(idPredicate)).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Document document) {
        deleteById(document.getId());
    }

    @Override
    @Transactional
    public void add(Document document) {
        System.out.println(document);
        em.persist(document);
    }

    @Override
    @Transactional
    public void update(Document documentNew) throws Exception {
        Document documentOld = findById(documentNew.getId());
        notNullFieldsEntityCopier.RefreshableFieldsCopy(documentNew, documentOld);

    }
}
