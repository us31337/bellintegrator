package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.OfficeDao;
import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.filter.OfficeFilter;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OfficeDaoImplTest {

    @Autowired
    private OfficeDao officeDao;

    @Test
    void findByFilter() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter(1L);
        officeFilter.setName("клининГ");
/*
        officeFilter.setPhone("+78124493110");
        officeFilter.setActive(true);
*/
        List<Office> list = officeDao.findByFilter(officeFilter);
        list.stream().forEach(s -> System.out.println(s.getName()));
    }

    @Test
    void findById() throws Exception {
        Office result = officeDao.findById(1L);
        System.out.println(result.getName());
    }

    @Test
    void findAll() throws NotFoundException {
        List<Office> result = officeDao.findAll();
        result.stream().forEach(s -> System.out.println(s.getName()));
    }
}