package bellintegrator.com.demo.dao;

import bellintegrator.com.demo.entity.Country;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    Country findById(Long id);
    Country findByName(String name) throws NotFoundException;
    Country findByCode(Integer code) throws NotFoundException;
    List<Country> findAll();
    void deleteById(Long id);
    void delete(Country country);
    void add(Country country);
    void update(Country country);
}
