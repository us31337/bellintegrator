package bellintegrator.com.demo.view.organisationdto;

import javax.validation.constraints.*;

/**
 * Класс для представления иформации для обновления данных организации
 *
 * @see bellintegrator.com.demo.entity.Organisation
 */
public class UpdateOrganisationDto {

    /**
     * Идентификатор организации
     * @see bellintegrator.com.demo.entity.Organisation#id
     */
    @NotNull(message = "Id is required")
    @Min(value = 1, message = "Id cannot be negative")
    private Long id;

    /**
     * Наименование организации
     * @see bellintegrator.com.demo.entity.Organisation#name
     */
    @NotEmpty(message = "Name is required")
    @Size(min = 2, message = "Minimum 2 characters name required")
    private String name;

    /**
     * Полное наименование организации
     * @see bellintegrator.com.demo.entity.Organisation#fullName
     */
    @NotEmpty(message = "Full name is required")
    @Size(min = 2, message = "Minimum 2 characters full name required")
    private String fullName;

    /**
     * ИНН организации
     * @see bellintegrator.com.demo.entity.Organisation#inn
     */
    @NotEmpty(message = "INN is required")
    @Size(min = 10, message = "Minimum 10 digits required for INN")
    @Pattern(regexp = "^[\\d]+")
    private String inn;

    /**
     *
     *  КПП организации
     * @see bellintegrator.com.demo.entity.Organisation#kpp
     */
    @NotEmpty(message = "KPP is required")
    @Size(min = 5, message = "Minimum 5 digits required for KPP")
    @Pattern(regexp = "^[\\d]+")
    private String kpp;

    /**
     * Адрес организации
     * @see bellintegrator.com.demo.entity.Organisation#address
     */
    @NotEmpty(message = "Address is required")
    @Size(min = 5, message = "Minimum 5 digits required for KPP")
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
