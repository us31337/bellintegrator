package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.OfficeDao;
import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.service.NotNullFieldsEntityCopier;
import bellintegrator.com.demo.view.filter.OfficeFilter;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    @PersistenceContext
    private EntityManager em;
    private NotNullFieldsEntityCopier<Office> notNullFieldsEntityCopier;

    /**
     * Подключение класса, копирующего значений не пустых обновляемых полей
     */
    @Autowired
    public OfficeDaoImpl(NotNullFieldsEntityCopier<Office> notNullFieldsEntityCopier) {
        this.notNullFieldsEntityCopier = notNullFieldsEntityCopier;
    }

    @Override
    public List<Office> findByFilter(OfficeFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> cq = cb.createQuery(Office.class);
        Root<Office> officeRoot = cq.from(Office.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getOrgId() != null && filter.getOrgId() > 0) {
            predicates.add(
                    cb.equal(
                            officeRoot.get("parentOrg").get("id"), filter.getOrgId()
                    )
            );
        }

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            String pattern = "%" + filter.getName().toLowerCase() + "%";
            predicates.add(cb.like(
                    cb.lower(officeRoot.get("name")), pattern)
            );
        }

        if (filter.getIsActive() != null) {
            predicates.add(cb.equal(officeRoot.get("isActive"), filter.getIsActive()));
        }

        if (filter.getPhone() != null && !filter.getPhone().isEmpty()) {
            predicates.add(cb.equal(officeRoot.get("phone"), filter.getPhone()));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[]{})));
        TypedQuery<Office> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Office findById(Long id) throws NotFoundException {
        Office result = em.find(Office.class, id);
        if (result != null) {
            return result;
        } else {
            throw new NotFoundException("Office with id " + id + " not found!");
        }
    }

    @Override
    public List<Office> findAll() throws NotFoundException {
        List<Office> result = em.createQuery("select o from Office o").getResultList();
        if (result.size() > 0) {
            return result;
        } else {
            throw new NotFoundException("No elements in table office");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Office> cd = cb.createCriteriaDelete(Office.class);
        Root<Office> officeRoot = cd.from(Office.class);

        Predicate idPredicate = cb.equal(officeRoot.get("id"), id);
        em.createQuery(cd.where(idPredicate)).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Office office) {
        deleteById(office.getOfficeId());
    }

    @Override
    @Transactional
    public void add(Office office) {
        em.persist(office);
    }

    @Override
    @Transactional
    public void update(Office officeNew) throws Exception {
        Office officeOld = findById(officeNew.getOfficeId());
        notNullFieldsEntityCopier.updatableFieldsCopy(officeNew, officeOld);
    }
}
