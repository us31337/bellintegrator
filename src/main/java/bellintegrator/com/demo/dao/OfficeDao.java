package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.filter.OfficeFilter;
import javassist.NotFoundException;

import java.util.List;

public interface OfficeDao {
    Office findById(Long id) throws Exception;
    List<Office> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(Office office);
    void add(Office office);
    void update(Office office) throws Exception;
    List<Office> findByFilter(OfficeFilter filter) throws Exception;

}