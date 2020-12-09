package bellintegrator.com.demo.view.filter;

import javax.validation.constraints.NotEmpty;

/**
 * Класс фильтра для поиска ораганизаций
 *
 * @see bellintegrator.com.demo.entity.Organisation
 */
public class OrganisationFilter {
    /**
     * Наименование организации
     * @see bellintegrator.com.demo.entity.Organisation#name
     */
    @NotEmpty(message = "Name field is required")
    private String name;
    /**
     * ИНН орагнизации
     * @see bellintegrator.com.demo.entity.Organisation#inn
     */
    private String inn;
    /**
     * Отметка активности организации
     * @see bellintegrator.com.demo.entity.Organisation#isActive
     */
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

}
