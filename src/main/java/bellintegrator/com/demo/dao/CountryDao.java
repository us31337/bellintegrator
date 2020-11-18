package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Country;
import javassist.NotFoundException;

import java.util.List;

public interface CountryDao {
    Country findById(Long id) throws NotFoundException;
    Country findByName(String name) throws NotFoundException;
    Country findByCode(Integer code) throws NotFoundException;
    List<Country> findAll() throws NotFoundException;
    void deleteById(Long id);
    void delete(Country country);
    void add(Country country);
    void update(Country country) throws Exception;

}
