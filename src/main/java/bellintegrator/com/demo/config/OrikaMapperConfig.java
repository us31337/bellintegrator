package bellintegrator.com.demo.config;

import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.view.officedto.ListOfficeDto;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.SingleOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * Класс с конфигурацией Orika mapper
 */
public class OrikaMapperConfig {

    /**
     * @return new Orika mapper bean
     */
    @Bean
    public MapperFacade getMapperFacade() {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        setMapperForOffice(mapperFactory);
        setMapperForUser(mapperFactory);
        return mapperFactory.getMapperFacade();
    }

    private void setMapperForUser(DefaultMapperFactory mapperFactory) {
        mapperFactory.classMap(User.class, SingleUserDto.class)
                .field("document.type.name", "docName")
                .field("document.docNumber", "docNumber")
                .field("document.docDate", "docDate")
                .field("country.name", "citizenshipName")
                .field("country.code", "citizenshipCode")
                .byDefault().register();
        mapperFactory.classMap(SaveUserDto.class, User.class)
                .exclude("docCode").exclude("docName").exclude("docDate")
                .exclude("citizenshipCode").byDefault().register();
        mapperFactory.classMap(UpdateUserDto.class, User.class)
                .exclude("id").exclude("officeId")
                .exclude("docName").exclude("docDate").exclude("docNumber")
                .exclude("citizenshipCode").byDefault().register();
    }


    private void setMapperForOffice(DefaultMapperFactory mapperFactory) {
        mapperFactory.classMap(Office.class, ListOfficeDto.class)
                .field("officeId", "id")
                .byDefault().register();
        mapperFactory.classMap(Office.class, SingleOfficeDto.class)
                .field("officeId", "id")
                .byDefault().register();
        mapperFactory.classMap(SaveOfficeDto.class, Office.class)
                .exclude("orgId")
                .byDefault().register();
        mapperFactory.classMap(UpdateOfficeDto.class, Office.class)
                .exclude("id")
                .byDefault().register();
    }

}
