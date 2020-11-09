package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.DocTypeDao;
import bellintegrator.com.demo.entity.DocumentType;
import javassist.NotFoundException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class DocTypeDaoImpl implements DocTypeDao {
    @PersistenceContext
    private EntityManager em;

    private List<DocumentType> findBy(Map<String, Object> attributes) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DocumentType> cq = cb.createQuery(DocumentType.class);
        Root<DocumentType> documentTypeRoot = cq.from(DocumentType.class);
        List<Predicate> predicates = new ArrayList<>();
        attributes.forEach((k, v) -> {
            if (documentTypeRoot.get(k) != null) {
                predicates.add(cb.equal(documentTypeRoot.get(k), v));
            }
        });
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<DocumentType> query = em.createQuery(cq);
        return query.getResultList();
    }


    @Override
    public DocumentType findById(Long id) {
        return em.find(DocumentType.class, id);
    }

    @Override
    public List<DocumentType> findAll() throws NotFoundException {
        List<DocumentType> result = findBy(new HashMap<>());
        if (result.size() > 0) {
            return result;
        } else {
            throw new NotFoundException("No elements un table Document type");
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
