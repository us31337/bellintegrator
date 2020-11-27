package bellintegrator.com.demo.service.impl;

import org.springframework.stereotype.Service;

@Service
public class OrganisationServiceIpl { //implements OrganisationService

    /*private final SimpleDateFormat SDF;
    private String datePattern;
    private OfficeDao officeDao;
    private CountryDao countryDao;
    private DocTypeDao docTypeDao;
    private DocumentDao documentDao;
    private UserDao userDao;
    private MapperFactory mapperFactory;

    @Autowired
    public OrganisationServiceIpl(OfficeDao officeDao, CountryDao countryDao,
                                  DocTypeDao docTypeDao, DocumentDao documentDao, UserDao userDao,
                                  @Value("${date.format.pattern}") String datePattern) {
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.docTypeDao = docTypeDao;
        SDF = new SimpleDateFormat(datePattern);
        this.documentDao = documentDao;
        this.userDao = userDao;
        this.mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @Override
    public List<ListUserDto> findByFilter(UserFilter userFilter) {
        List<User> userList = userDao.findByFilter(userFilter);
        mapperFactory.classMap(User.class, ListUserDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        List<ListUserDto> collect = userList.stream().map(u -> mapper.map(u, ListUserDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SingleUserDto findById(Long id) throws NotFoundException {
        User user = userDao.findById(id);
        mapperFactory.classMap(User.class, SingleUserDto.class)
                .field("document.type.name", "docName")
                .field("document.docNumber", "docNumber")
                .field("document.docDate", "docDate")
                .field("country.name", "citizenshipName")
                .field("country.code", "citizenshipCode")
                .byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        *//*mapper.typeMap(User.class, SingleUserDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getDocument().getType().getName(),
                    SingleUserDto::setDocName);
            mapper.map(src -> src.getDocument().getDocNumber(),
                    SingleUserDto::setDocNumber);
            mapper.map(src -> src.getDocument().getDocDate(),
                    SingleUserDto::setDocDate);
            mapper.map(src -> src.getCountry().getName(),
                    SingleUserDto::setCitizenshipName);
            mapper.map(src -> src.getCountry().getCode(),
                    SingleUserDto::setCitizenshipCode);
        });*//*
        return mapper.map(user, SingleUserDto.class);
    }


    @Override
    public User mapUserSaveDto2User(SaveUserDto saveUserDto) throws Exception {
        User user = new User();
        Document document = new Document();
        user.setOffice(officeDao.findById(saveUserDto.getOfficeId()));
        document.setUser(user);
        document.setType(saveUserDto.getDocCode() != null ? docTypeDao.findByCode(saveUserDto.getDocCode()) : null);
        if (!document.getType().getName().equals(saveUserDto.getDocName())) {
            throw new IllegalArgumentException("Document's name not equal name from database");
        }
        document.setDocNumber(saveUserDto.getDocNumber());
        document.setDocDate(saveUserDto.getDocDate());
        Country country = null;
        if (saveUserDto.getCitizenshipCode() != null && saveUserDto.getCitizenshipCode() > 0) {
            country = countryDao.findByCode(saveUserDto.getCitizenshipCode());
        }
        user.setCountry(country);
        user.setFirstName(saveUserDto.getFirstName());
        user.setMiddleName(saveUserDto.getMiddleName());
        user.setLastName(saveUserDto.getLastName());
        user.setPhone(saveUserDto.getPhone());
        user.setPosition(saveUserDto.getPosition());
        user.setIdentified(saveUserDto.getIdentified() != null ? saveUserDto.getIdentified() : true);
        return user;
    }

    @Override
    public void saveUser(User user) {
        userDao.add(user);
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
        if (!document.getType().getName().equals(updateUserDto.getDocName())) {
            throw new IllegalArgumentException("Document's name not equal name from database");
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
    }*/
}
