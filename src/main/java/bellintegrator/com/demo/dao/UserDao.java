package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.filter.OrganisationFilter;
import bellintegrator.com.demo.filter.UserFilter;
import javassist.NotFoundException;

import java.util.List;

public interface UserDao {
    User findById(Long id);
    List<User> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(User docType);
    void add(User docType);
    void update(User docType);
    List<User> findByFilter(OrganisationFilter filter);
}
