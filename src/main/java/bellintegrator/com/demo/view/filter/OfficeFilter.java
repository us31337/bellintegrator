package bellintegrator.com.demo.view.filter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Класс фильтра для поиска офисов
 *
 * @see bellintegrator.com.demo.entity.Office
 */
public class OfficeFilter {

    /**
     * Первичный ключ родительсткой организации
     * @see bellintegrator.com.demo.entity.Office#officeId
     */
    @NotNull(message = "Id is required")
    @Min(value = 1, message = "Id cannot be negative")
    private Long orgId;
    /**
     * Наименование офиса
     * @see bellintegrator.com.demo.entity.Office#name
     */
    private String name;
    /**
     * Телефон офиса
     * @see bellintegrator.com.demo.entity.Office#phone
     */
    private String phone;
    /**
     * Отметка активности офиса
     * @see bellintegrator.com.demo.entity.Office#isActive
     */
    private Boolean isActive;

    public OfficeFilter() {
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
