package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.DocTypeDao;
import bellintegrator.com.demo.entity.DocumentType;
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

/**
 * {@inheritDoc}
 */
@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    @PersistenceContext
    private EntityManager em;
    private NotNullFieldsEntityCopier<DocumentType> notNullFieldsEntityCopier;

    /**
     * Подключение класса, копирующего значений не пустых обновляемых полей
     */
    @Autowired
    public DocTypeDaoImpl(NotNullFieldsEntityCopier<DocumentType> notNullFieldsEntityCopier) {
        this.notNullFieldsEntityCopier = notNullFieldsEntityCopier;
    }

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
    public DocumentType findByName(String name) throws NotFoundException {
        Query query = em.createQuery(
                "select d from DocumentType d where lower(d.name) like lower(:name)");
        query.setParameter("name", "%" + name + "%");
        List<DocumentType> result = query.getResultList();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            throw new NotFoundException("Document type with name " + name + " not found!");
        }
    }

    @Override
    public DocumentType findByCode(String code) throws NotFoundException {
        Query query = em.createQuery("select d from DocumentType d where d.code = :code");
        query.setParameter("code", code);
        List<DocumentType> result = query.getResultList();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            throw new NotFoundException("Document type with code " + code + " not found!");
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
    public void update(DocumentType documentTypeNew) throws Exception {
        DocumentType documentTypeOld = findById(documentTypeNew.getId());
        notNullFieldsEntityCopier.updatableFieldsCopy(documentTypeNew, documentTypeOld);
    }
}
