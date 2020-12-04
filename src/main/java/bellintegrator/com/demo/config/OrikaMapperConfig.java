package bellintegrator.com.demo.config;

import bellintegrator.com.demo.entity.Office;
import bellintegrator.com.demo.entity.Organisation;
import bellintegrator.com.demo.entity.User;
import bellintegrator.com.demo.view.officedto.ListOfficeDto;
import bellintegrator.com.demo.view.officedto.SaveOfficeDto;
import bellintegrator.com.demo.view.officedto.SingleOfficeDto;
import bellintegrator.com.demo.view.officedto.UpdateOfficeDto;
import bellintegrator.com.demo.view.organisationdto.ListOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SaveOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.SingleOrganisationDto;
import bellintegrator.com.demo.view.organisationdto.UpdateOrganisationDto;
import bellintegrator.com.demo.view.userdto.ListUserDto;
import bellintegrator.com.demo.view.userdto.SaveUserDto;
import bellintegrator.com.demo.view.userdto.SingleUserDto;
import bellintegrator.com.demo.view.userdto.UpdateUserDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaMapperConfig {

    @Bean
    public MapperFacade getMapperFacade() {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        setMapperForOffice(mapperFactory);
        setMapperForOrganistion(mapperFactory);
        setMapperForUser(mapperFactory);
        return mapperFactory.getMapperFacade();
    }

    private void setMapperForUser(DefaultMapperFactory mapperFactory) {
        mapperFactory.classMap(User.class, ListUserDto.class);
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

    private void setMapperForOrganistion(DefaultMapperFactory mapperFactory) {
        mapperFactory.classMap(Organisation.class, ListOrganisationDto.class);
        mapperFactory.classMap(Organisation.class, SingleOrganisationDto.class);
        mapperFactory.classMap(SaveOrganisationDto.class, Organisation.class);
        mapperFactory.classMap(UpdateOrganisationDto.class, Organisation.class);
    }

    private void setMapperForOffice(DefaultMapperFactory mapperFactory) {
        mapperFactory.classMap(Office.class, ListOfficeDto.class)
                .field("parentOrg.id", "orgId")
                .byDefault().register();
        mapperFactory.classMap(Office.class, SingleOfficeDto.class);
        mapperFactory.classMap(SaveOfficeDto.class, Office.class)
                .exclude("orgId")
                .byDefault().register();
        mapperFactory.classMap(UpdateOfficeDto.class, Office.class)
                .exclude("id")
                .byDefault().register();
    }

}
