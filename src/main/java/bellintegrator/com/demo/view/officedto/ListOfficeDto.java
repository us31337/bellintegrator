package bellintegrator.com.demo.view.officedto;

/**
 * Класс для представления ифнормации о списке офисов
 *
 * @see bellintegrator.com.demo.entity.Office
 */
public class ListOfficeDto {

    /**
     * Первичный ключ офиса
     * @see bellintegrator.com.demo.entity.Office#officeId
     */
    private Long id;
    /**
     * Наименование офиса
     * @see bellintegrator.com.demo.entity.Office#name
     */
    private String name;
    /**
     * Отметка активности офиса
     * @see bellintegrator.com.demo.entity.Office#isActive
     */
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
