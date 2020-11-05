package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.CountryDao;
import bellintegrator.com.demo.entity.Country;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CountryDaoImpl implements CountryDao {

    @Autowired
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
    public Country findById(Long id) {
        return em.find(Country.class, id);
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
            throw new NotFoundException("Country with name " + code + " not found!");
        }
    }

    @Override
    public List<Country> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> countryRoot = cq.from(Country.class);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Country> cd = cb.createCriteriaDelete(Country.class);
        Root<Country> countryRoot = cd.from(Country.class);

        Predicate idPredicate = cb.equal(countryRoot.get("id"), id);
        em.createQuery(cd.where(idPredicate)).executeUpdate();
    }

    @Override
    public void delete(Country country) {
        deleteById(country.getId());
    }

    @Override
    public void add(Country country) {
        em.persist(country);
    }

    @Override
    public void update(Country country) {
        em.merge(country);
    }
}
