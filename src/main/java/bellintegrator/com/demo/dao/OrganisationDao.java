package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.filter.OrganisationFilter;
import javassist.NotFoundException;

import java.util.List;

public interface OrganisationDao {
    Organisation findById(Long id);
    List<Organisation> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(Organisation docType);
    void add(Organisation docType);
    void update(Organisation docType);
    List<Organisation> findByFilter(OrganisationFilter filter);

}
