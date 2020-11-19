package bellintegrator.com.demo.dao.impl;

import bellintegrator.com.demo.dao.UserDao;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.filter.UserFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    void findByFilter() {
        UserFilter filter = new UserFilter();
        filter.setOfficeId(2L);
//        filter.setFirstName("катерина");
//        filter.setDocCode("09");
//        filter.setCitizenshipCode(156);
        List<User> users = userDao.findByFilter(filter);
        users.stream().forEach(s -> System.out.println(s.getFirstName()));
        System.out.println(users.size());
    }
}