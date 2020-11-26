package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.view.filter.OrganisationFilter;
import javassist.NotFoundException;

import java.util.List;

public interface OrganisationDao {
    Organisation findById(Long id) throws NotFoundException;
    List<Organisation> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(Organisation organisation);
    void add(Organisation organisation);
    void update(Organisation organisation) throws Exception;
    List<Organisation> findByFilter(OrganisationFilter filter);

}
