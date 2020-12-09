package bellintegrator.com.demo.view.organisationdto;

/**
 * Класс для представления ифнормации о списке организаций
 *
 * @see bellintegrator.com.demo.entity.Organisation
 */
public class ListOrganisationDto {

    /**
     * Идентификатор организации
     * @see bellintegrator.com.demo.entity.Organisation#id
     */
    private Long id;

    /**
     * Наименование организации
     * @see bellintegrator.com.demo.entity.Organisation#name
     */
    private String name;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
