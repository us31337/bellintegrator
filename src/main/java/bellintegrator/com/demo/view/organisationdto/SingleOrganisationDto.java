package bellintegrator.com.demo.view.organisationdto;

/**
 * Класс для представления иформации об отдельной организации
 *
 * @see bellintegrator.com.demo.entity.Organisation
 */
public class SingleOrganisationDto {

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
     * Полное наименование организации
     * @see bellintegrator.com.demo.entity.Organisation#fullName
     */
    private String fullName;

    /**
     * ИНН организации
     * @see bellintegrator.com.demo.entity.Organisation#inn
     */
    private String inn;

    /**
     * КПП организации
     * @see bellintegrator.com.demo.entity.Organisation#kpp
     */
    private String kpp;

    /**
     * Адрес организации
     * @see bellintegrator.com.demo.entity.Organisation#address
     */
    private String address;

    /**
     * Телефон организации
     * @see bellintegrator.com.demo.entity.Organisation#phone
     */
    private String phone;

    /**
     * Отметка активности организации
     * @see bellintegrator.com.demo.entity.Organisation#isActive
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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
