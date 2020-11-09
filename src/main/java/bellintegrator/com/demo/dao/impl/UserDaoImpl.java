package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.UserDao;
import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.filter.OrganisationFilter;
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
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    private List<User> findBy(Map<String, Object> attributes) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        attributes.forEach((k, v) -> {
            if (userRoot.get(k) != null) {
                predicates.add(cb.equal(userRoot.get(k), v));
            }
        });
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }
    
    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() throws NotFoundException {
        List<User> list = findBy(new HashMap<>());
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
    public void delete(User docType) {

    }

    @Override
    @Transactional
    public void add(User docType) {

    }

    @Override
    @Transactional
    public void update(User docType) {

    }

    @Override
    public List<User> findByFilter(OrganisationFilter filter) {
        return null;
    }
}
