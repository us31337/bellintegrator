package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.CountryDao;
import bellintegrator.com.demo.entity.Country;
import javassist.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CountryDaoImpl implements CountryDao {

    @PersistenceContext
    private EntityManager em;

    private List<Country> findBy(Map<String, Object> attributes) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> countryRoot = cq.from(Country.class);
        List<Predicate> predicates = new ArrayList<>();
        attributes.forEach((k, v) -> {
            if (countryRoot.get(k) != null) {
                predicates.add(cb.equal(countryRoot.get(k), v));
            }
        });
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Country> query = em.createQuery(cq);
        return query.getResultList();
    }


    @Override
    public Country findById(Long id) throws NotFoundException {
        Country result = em.find(Country.class, id);
        if (result != null) {
            return result;
        } else {
            throw new NotFoundException("Country with id " + id + " not found!");
        }
    }

    @Override
    public Country findByName(String name) throws NotFoundException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", name);
        List<Country> result = findBy(attributes);
        if (result.size() > 0) {
            return result.get(0);
        } else {
            throw new NotFoundException("Country with name " + name + " not found!");
        }
    }

    @Override
    public Country findByCode(Integer code) throws NotFoundException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("code", code);
        List<Country> result = findBy(attributes);
        if (result.size() > 0) {
            return result.get(0);
        } else {
            throw new NotFoundException("Country with code " + code + " not found!");
        }
    }

    @Override
    public List<Country> findAll() throws NotFoundException {
        Query query = em.createQuery("select c from Country c");
        List<Country> result = query.getResultList();
        if (result.size() > 0) {
            return result;
        } else {
            throw new NotFoundException("No elements in table Country");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Country> cd = cb.createCriteriaDelete(Country.class);
        Root<Country> countryRoot = cd.from(Country.class);

        Predicate idPredicate = cb.equal(countryRoot.get("id"), id);
        em.createQuery(cd.where(idPredicate)).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Country country) {
        deleteById(country.getId());
    }

    @Override
    @Transactional
    public void add(Country country) {
        em.persist(country);
    }

    @Override
    @Transactional
    public void update(Country country) {
        em.merge(country);
    }
}
