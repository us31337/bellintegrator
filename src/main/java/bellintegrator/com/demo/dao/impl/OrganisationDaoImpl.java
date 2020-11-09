package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.OrganisationDao;
import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.filter.OrganisationFilter;
import javassist.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrganisationDaoImpl implements OrganisationDao {

    @PersistenceContext
    private EntityManager em;

    private List<Organisation> findBy(Map<String, Object> attributes) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organisation> cq = cb.createQuery(Organisation.class);
        Root<Organisation> organisationRoot = cq.from(Organisation.class);
        List<Predicate> predicates = new ArrayList<>();
        attributes.forEach((k, v) -> {
            if (organisationRoot.get(k) != null) {
                predicates.add(cb.equal(organisationRoot.get(k), v));
            }
        });
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Organisation> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Organisation findById(Long id) {
        return em.find(Organisation.class, id);
    }

    @Override
    public List<Organisation> findByFilter(OrganisationFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organisation> cq = cb.createQuery(Organisation.class);
        Root<Organisation> organisationRoot = cq.from(Organisation.class);
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() == null || filter.getName().isEmpty()) {
            throw new IllegalArgumentException("Field name is required");
        } else {
            String pattern = "%" + filter.getName().toUpperCase() + "%";
            predicates.add(cb.like(organisationRoot.get("name"), pattern));
        }

        if (filter.getInn() != null) {
            predicates.add(cb.equal(organisationRoot.get("inn"), filter.getInn()));
        }
        if (filter.isActive() != null) {
            Path<Object> path = organisationRoot.get("isActive");
            predicates.add(cb.equal(organisationRoot.get("isActive"), filter.isActive()));
        }
        cq.where(cb.and(predicates.toArray(new Predicate[]{})));
        TypedQuery<Organisation> query = em.createQuery(cq);
        return query.getResultList();
    }


    @Override
    public List<Organisation> findAll() throws NotFoundException {
        Query query = em.createQuery("select o from Organisation o");
        List<Organisation> list = query.getResultList();
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
    public void delete(Organisation docType) {

    }

    @Override
    @Transactional
    public void add(Organisation docType) {

    }

    @Override
    @Transactional
    public void update(Organisation docType) {
    }
}
