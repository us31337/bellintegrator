package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.DocumentType;
import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.filter.OfficeFilter;
import bellintegrator.com.demo.filter.OrganisationFilter;
import javassist.NotFoundException;

import java.util.List;

public interface OfficeDao {
    Office findById(Long id);
    List<Office> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(Office docType);
    void add(Office docType);
    void update(Office docType);
    List<Office> findByFilter(OfficeFilter filter);

}
