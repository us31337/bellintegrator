package bellintegrator.com.demo.view.officedto;

/**
 * Класс для представления ифнормации об отдельном офисе
 *
 * @see bellintegrator.com.demo.entity.Office
 */
public class SingleOfficeDto {

    /**
     * Идентификатор офиса
     * @see bellintegrator.com.demo.entity.Office#officeId
     */
    private Long id;

    /**
     * Наименование офиса
     * @see bellintegrator.com.demo.entity.Office#name
     */
    private String name;

    /**
     * Адрес меcтонахождения офиса
     * @see bellintegrator.com.demo.entity.Office#address
     */
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
