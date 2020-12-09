package bellintegrator.com.demo.service.impl;

import bellintegrator.com.demo.dao.CountryDao;
import bellintegrator.com.demo.dao.DocTypeDao;
import bellintegrator.com.demo.dao.OfficeDao;
import bellintegrator.com.demo.dao.UserDao;
import bellintegrator.com.demo.entity.Country;
import bellintegrator.com.demo.entity.Document;
import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.service.UserService;
import bellintegrator.com.demo.view.filter.UserFilter;
import bellintegrator.com.demo.view.userdto.ListUserDto;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import javassist.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceIpl implements UserService {

    private OfficeDao officeDao;
    private CountryDao countryDao;
    private DocTypeDao docTypeDao;
    private UserDao userDao;
    private MapperFacade mapper;

    /**
     * @param officeDao  слой работы с базой данных офисов
     * @param countryDao слой работы с базой данных стран
     * @param docTypeDao слой работы с базой данных типов документов
     * @param userDao    слой работы с базой данных полььзователей
     * @param mapper     преобразователь представлений в сущности
     */
    @Autowired
    public UserServiceIpl(OfficeDao officeDao, CountryDao countryDao,
                          DocTypeDao docTypeDao, UserDao userDao, MapperFacade mapper) {
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.docTypeDao = docTypeDao;
        this.userDao = userDao;
        this.mapper = mapper;
    }

    @Override
    public List<ListUserDto> findByFilter(UserFilter userFilter) {
        List<User> userList = userDao.findByFilter(userFilter);
        List<ListUserDto> collect = userList.stream().map(u -> mapper.map(u, ListUserDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SingleUserDto findById(Long id) throws NotFoundException {
        User user = userDao.findById(id);
        SingleUserDto userDto = mapper.map(user, SingleUserDto.class);
        return userDto;
    }


    @Override
    public void mapAndSaveUserDto(SaveUserDto saveUserDto) throws Exception {
        User user = new User();
        Document document = new Document();
        user.setOffice(officeDao.findById(saveUserDto.getOfficeId()));
        document.setUser(user);
        user.setDocument(document);
        document.setType(saveUserDto.getDocCode() != null ? docTypeDao.findByCode(saveUserDto.getDocCode()) : null);
        if (!document.getType().getName().equals(saveUserDto.getDocName())) {
            throw new IllegalArgumentException("Document's name not equal name from database");
        }
        document.setDocNumber(saveUserDto.getDocNumber());
        Date docDate = saveUserDto.getDocDate();
        if (docDate != null) {
            document.setDocDate(docDate);
        } else {
            throw new IllegalArgumentException("Null date in document is not allowed");
        }
        Country country = null;
        if (saveUserDto.getCitizenshipCode() != null && saveUserDto.getCitizenshipCode() > 0) {
            country = countryDao.findByCode(saveUserDto.getCitizenshipCode());
        }
        user.setCountry(country);
        mapper.map(saveUserDto, user);
        userDao.add(user);
    }

    @Override
    public void mapAndUpdateUserDto(UpdateUserDto updateUserDto) throws Exception {
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
        if (docNumber != null && Strings.isNotBlank(docNumber)) {
            document.setDocNumber(docNumber);
        }
        String newDocumentName = updateUserDto.getDocName();
        if (newDocumentName == null || newDocumentName != null
                && !newDocumentName.equals(user.getDocument().getType().getName())) {
            throw new IllegalArgumentException("Document's name not equal name from database");
        }
        mapper.map(updateUserDto, user);
        userDao.update(user);
    }

}
