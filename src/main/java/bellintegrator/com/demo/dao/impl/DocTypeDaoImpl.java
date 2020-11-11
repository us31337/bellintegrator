package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.DocTypeDao;
import bellintegrator.com.demo.entity.DocumentType;
import javassist.NotFoundException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class DocTypeDaoImpl implements DocTypeDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public DocumentType findById(Long id) throws NotFoundException {
        DocumentType result = em.find(DocumentType.class, id);
        if (result != null) {
            return result;
        } else {
            throw new NotFoundException("Document type with id " + id + " not found!");
        }
    }

    @Override
    public List<DocumentType> findAll() throws NotFoundException {
        Query query = em.createQuery("select dt from DocumentType dt");
        List<DocumentType> result = query.getResultList();
        if (result.size() > 0) {
            return result;
        } else {
            throw new NotFoundException("No elements in table Document type");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<DocumentType> cd = cb.createCriteriaDelete(DocumentType.class);
        Root<DocumentType> countryRoot = cd.from(DocumentType.class);

        Predicate idPredicate = cb.equal(countryRoot.get("id"), id);
        em.createQuery(cd.where(idPredicate)).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(DocumentType documentType) {
        deleteById(documentType.getId());
    }

    @Override
    @Transactional
    public void add(DocumentType documentType) {
        em.persist(documentType);
    }

    @Override
    @Transactional
    public void update(DocumentType documentType){
        em.merge(documentType);
    }
}
