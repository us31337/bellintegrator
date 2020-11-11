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
import java.util.List;
import java.util.Map;

@Repository
public class OrganisationDaoImpl implements OrganisationDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Organisation findById(Long id) throws NotFoundException {
        Organisation result =  em.find(Organisation.class, id);
        if (result != null) {
            return result;
        } else {
            throw new NotFoundException("Organisation type with id " + id + " not found!");
        }
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Organisation> cd = cb.createCriteriaDelete(Organisation.class);
        Root<Organisation> organisationRoot = cd.from(Organisation.class);

        Predicate idPredicate = cb.equal(organisationRoot.get("id"), id);
        em.createQuery(cd.where(idPredicate)).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Organisation organisation) {
        deleteById(organisation.getId());
    }

    @Override
    @Transactional
    public void add(Organisation organisation) {
        em.persist(organisation);
    }

    @Override
    @Transactional
    public void update(Organisation organisation) {
        em.merge(organisation);
    }
}
