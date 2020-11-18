package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.OrganisationDao;
import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.filter.OrganisationFilter;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OrganisationDaoImplTest {

    @Autowired
    private OrganisationDao organisationDao;

    @Test
    void findById() throws NotFoundException {
        Organisation org = organisationDao.findById(1000L);
        assertTrue(org.getId() == 1);
    }

    @Test
    void findByFilter() {
        OrganisationFilter filter = new OrganisationFilter();
//        filter.setActive(true);
        filter.setName("ИбИсЛаБ");
        List<Organisation> list = organisationDao.findByFilter(filter);
        System.out.println(list);

    }

    @Test
    void findAll() throws NotFoundException {
        List<Organisation> list = organisationDao.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }
}