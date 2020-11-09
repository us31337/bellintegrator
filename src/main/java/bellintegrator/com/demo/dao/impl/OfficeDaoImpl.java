package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.OfficeDao;
import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.filter.OfficeFilter;
import javassist.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    @PersistenceContext
    private EntityManager em;

    private List<Office> findBy(Map<String, Object> attributes) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> cq = cb.createQuery(Office.class);
        Root<Office> officeRoot = cq.from(Office.class);
        List<Predicate> predicates = new ArrayList<>();
        attributes.forEach((k, v) -> {
            if (officeRoot.get(k) != null) {
                predicates.add(cb.equal(officeRoot.get(k), v));
            }
        });
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Office> query = em.createQuery(cq);
        return query.getResultList();
    }


    @Override
    public List<Office> findByFilter(OfficeFilter filter) {
        return null;
    }


    @Override
    public Office findById(Long id) {
        return em.find(Office.class, id);
    }

    @Override
    public List<Office> findAll() throws NotFoundException {
        List<Office> list = findBy(new HashMap<>());
        if (list.size() > 0) {
            return list;
        } else {
            throw new NotFoundException("No elements in table office");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Office docType) {

    }

    @Override
    @Transactional
    public void add(Office docType) {

    }

    @Override
    @Transactional
    public void update(Office docType) {

    }
}
