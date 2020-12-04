package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.UserDao;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.service.NotNullFieldsEntityCopier;
import bellintegrator.com.demo.view.filter.UserFilter;
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

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;
    private NotNullFieldsEntityCopier<User> notNullFieldsEntityCopier;

    @Autowired
    public UserDaoImpl(NotNullFieldsEntityCopier<User> notNullFieldsEntityCopier) {
        this.notNullFieldsEntityCopier = notNullFieldsEntityCopier;
    }

    @Override
    public User findById(Long id) throws NotFoundException {
        User user = em.find(User.class, id);
        if (user != null) {
            return user;
        } else {
            throw new NotFoundException("User with id " + id + " not found!");
        }
    }

    @Override
    public List<User> findAll() throws NotFoundException {
        List<User> list = em.createQuery("select u from User u").getResultList();
        if (list.size() > 0) {
            return list;
        } else {
            throw new NotFoundException("No elements in table users");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<User> cd = cb.createCriteriaDelete(User.class);
        Root<User> userRoot = cd.from(User.class);

        Predicate idPredicate = cb.equal(userRoot.get("id"), id);
        em.createQuery(cd.where(idPredicate)).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(User user) {
        deleteById(user.getId());
    }

    @Override
    @Transactional
    public void add(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void update(User userNew) throws Exception {
        User userOld = findById(userNew.getId());
        notNullFieldsEntityCopier.updatableFieldsCopy(userNew, userOld);
    }

    @Override
    public List<User> findByFilter(UserFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getOfficeId() != null) {
            Path<Object> objectPath = userRoot.get("office").get("officeId");
            predicates.add(
                    cb.equal(userRoot.get("office").get("officeId"), filter.getOfficeId()
                    )
            );
        } else {
            throw new IllegalArgumentException("Office id cannot be empty!");
        }

        if (filter.getFirstName() != null && !filter.getFirstName().isEmpty()) {
            String pattern = "%" + filter.getFirstName().toLowerCase() + "%";
            predicates.add(cb.like(
                    cb.lower(userRoot.get("firstName")), pattern)
            );
        }

        if (filter.getLastName() != null && !filter.getLastName().isEmpty()) {
            String pattern = "%" + filter.getLastName().toLowerCase() + "%";
            predicates.add(cb.like(
                    cb.lower(userRoot.get("lastName")), pattern)
            );
        }

        if (filter.getMiddleName() != null && !filter.getMiddleName().isEmpty()) {
            String pattern = "%" + filter.getMiddleName().toLowerCase() + "%";
            predicates.add(cb.like(
                    cb.lower(userRoot.get("middleName")), pattern)
            );
        }

        if (filter.getPosition() != null && !filter.getPosition().isEmpty()) {
            String pattern = "%" + filter.getPosition().toLowerCase() + "%";
            predicates.add(cb.like(
                    cb.lower(userRoot.get("position")), pattern)
            );
        }

        if (filter.getDocCode() != null && !filter.getDocCode().isEmpty()) {
            predicates.add(cb.equal(userRoot.get("document")
                    .get("type")
                    .get("code"), filter.getDocCode()));
        }

        if (filter.getCitizenshipCode() != null && filter.getCitizenshipCode() > 0) {
            predicates.add(cb.equal(userRoot.get("country")
                    .get("code"), filter.getCitizenshipCode()));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[]{})));
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }
}
