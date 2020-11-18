package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.filter.OrganisationFilter;
import bellintegrator.com.demo.filter.UserFilter;
import javassist.NotFoundException;

import java.util.List;

public interface UserDao {
    User findById(Long id) throws NotFoundException;
    List<User> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(User user);
    void add(User user);
    void update(User user) throws Exception;
    List<User> findByFilter(UserFilter filter);
}
