package bellintegrator.com.demo.service.impl;

import bellintegrator.com.demo.dao.*;
import bellintegrator.com.demo.entity.Country;
import bellintegrator.com.demo.entity.Document;
import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.filter.UserFilter;
import bellintegrator.com.demo.service.UserService;
import bellintegrator.com.demo.view.UpdateUserDto;
import bellintegrator.com.demo.view.UserSaveDto;
import javassist.NotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceIpl implements UserService {

    private final SimpleDateFormat SDF;
    private String datePattern;
    private OfficeDao officeDao;
    private CountryDao countryDao;
    private DocTypeDao docTypeDao;
    private DocumentDao documentDao;
    private UserDao userDao;

    @Autowired
    public UserServiceIpl(OfficeDao officeDao, CountryDao countryDao,
                          DocTypeDao docTypeDao, DocumentDao documentDao, UserDao userDao,
                          @Value("${date.format.pattern}") String datePattern) {
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.docTypeDao = docTypeDao;
        SDF = new SimpleDateFormat(datePattern);
        this.documentDao = documentDao;
        this.userDao = userDao;
    }

    @Override
    public List<User> findByFilter(UserFilter userFilter) {
        return userDao.findByFilter(userFilter);
    }

    @Override
    public User findById(Long id) throws NotFoundException {
        return userDao.findById(id);
    }

    @Override
    public boolean validateSaveUserDto(UserSaveDto userSaveDto) {
        Long officeId = userSaveDto.getOfficeId();
        boolean first = officeId != null && officeId > 0;
        String firstName = userSaveDto.getFirstName();
        boolean second = Strings.isNotBlank(firstName);
        String position = userSaveDto.getPosition();
        boolean third = Strings.isNotBlank(position);
        return first && second && third;
    }

    @Override
    public User mapUserSaveDto2User(UserSaveDto userSaveDto) throws Exception {
        User user = new User();
        Document document = new Document();
        user.setOffice(officeDao.findById(userSaveDto.getOfficeId()));
        document.setUser(user);
        document.setType(userSaveDto.getDocCode() != null ? docTypeDao.findByCode(userSaveDto.getDocCode()) : null);
        document.setDocNumber(userSaveDto.getDocNumber());
        document.setDocDate(userSaveDto.getDocDate());
        Country country = null;
        if (userSaveDto.getCitizenshipCode() != null && userSaveDto.getCitizenshipCode() > 0) {
            country = countryDao.findByCode(userSaveDto.getCitizenshipCode());
        }
        user.setCountry(country);
        user.setFirstName(userSaveDto.getFirstName());
        user.setMiddleName(userSaveDto.getMiddleName());
        user.setLastName(userSaveDto.getLastName());
        user.setPhone(userSaveDto.getPhone());
        user.setPosition(userSaveDto.getPosition());
        user.setIdentified(userSaveDto.getIdentified() != null ? userSaveDto.getIdentified() : true);
        return user;
    }

    @Override
    public void saveUser(User user) {
        userDao.add(user);
    }

    @Override
    public boolean validateUpdateUserDto(UpdateUserDto updateUserDto) {
        Long id = updateUserDto.getId();
        boolean first = id != null && id > 0;
        String firstName = updateUserDto.getFirstName();
        boolean second = Strings.isNotBlank(firstName);
        String position = updateUserDto.getPosition();
        boolean third = Strings.isNotBlank(position);
        return first && second && third;
    }

    @Override
    public User mapUserUpdateDto2User(UpdateUserDto updateUserDto) throws Exception {
        User user = userDao.findById(updateUserDto.getId());

        Long id = updateUserDto.getOfficeId();
        if (id != null && id > 0) {
            Office office = officeDao.findById(updateUserDto.getId());
            user.setOffice(office);
        }
        Integer citizenshipCode = updateUserDto.getCitizenshipCode();
        if (citizenshipCode != null && citizenshipCode > 0) {
            Country country = countryDao.findByCode(citizenshipCode);
            user.setCountry(country);
        }

        Document document = user.getDocument();
        Date docDate = updateUserDto.getDocDate();
        if (docDate != null) {
            document.setDocDate(docDate);
        }
        String docNumber = updateUserDto.getDocNumber();
        if (Strings.isNotBlank(docNumber)) {
            document.setDocNumber(docNumber);
        }

        user.setFirstName(updateUserDto.getFirstName());
        String middleName = updateUserDto.getMiddleName();
        if (Strings.isNotBlank(middleName)) {
            user.setMiddleName(middleName);
        }

        String lastName = updateUserDto.getLastName();
        if (Strings.isNotBlank(lastName)) {
            user.setLastName(lastName);
        }

        String position = updateUserDto.getPosition();
        if (Strings.isNotBlank(position)) {
            user.setPosition(position);
        }

        String phone = updateUserDto.getPhone();
        if (Strings.isNotBlank(phone)) {
            user.setPhone(phone);
        }

        Boolean identified = updateUserDto.getIdentified();
        if (identified != null) {
            user.setIdentified(identified);
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws Exception {
        userDao.update(user);
    }
}
