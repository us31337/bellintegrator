package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.CountryDao;
import bellintegrator.com.demo.entity.Country;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryDaoImplTest {
    @Autowired
    private CountryDao countryDao;

    @Test
    void findAll() {
        List<Country> countries = countryDao.findAll();
        System.out.println(Arrays.toString(countries.toArray()));
        assertTrue(countries.size() > 0);
    }

    @Test
    void findById() {
        Country country = countryDao.findById(10L);
        System.out.println(country.getName());
    }

    @Test
    void findByName() {
        try {
            Country USA = countryDao.findByName("США");
            assertTrue(USA.getName().equals("США"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findByCode() {
        try {
            Country USA = countryDao.findByCode(840);
            assertTrue(USA.getCode() == 840);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}