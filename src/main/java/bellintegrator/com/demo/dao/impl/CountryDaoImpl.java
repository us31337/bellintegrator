package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.CountryDao;
import bellintegrator.com.demo.entity.Country;
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

@Repository
public class CountryDaoImpl implements CountryDao {

    @PersistenceContext
    private EntityManager em;
    private NotNullFieldsEntityCopier<Country> notNullFieldsEntityCopier;

    @Autowired
    public CountryDaoImpl(NotNullFieldsEntityCopier<Country> notNullFieldsEntityCopier) {
        this.notNullFieldsEntityCopier = notNullFieldsEntityCopier;
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
        Query query = em.createQuery(
                "select c from Country c where lower(c.name) like lower(:name)");
        query.setParameter("name", "%" + name + "%");
        List<Country> result = query.getResultList();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            throw new NotFoundException("Country with name " + name + " not found!");
        }
    }

    @Override
    public Country findByCode(Integer code) throws NotFoundException {
        Query query = em.createQuery("select c from Country c where c.code = :code");
        query.setParameter("code", code);
        List<Country> result = query.getResultList();
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
    public void update(Country countryNew) throws Exception {
        Country country = findById(countryNew.getId());
        notNullFieldsEntityCopier.updatableFieldsCopy(countryNew, country);
    }
}
