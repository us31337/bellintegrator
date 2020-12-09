package bellintegrator.com.demo.entity;

import bellintegrator.com.demo.annotaion.Refreshable;

import javax.persistence.*;

/**
 * Класс для описания сущности организации
 */
@Entity
@Table(name = "organisation")
public class Organisation {

    /**
     * Первичный ключ
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * Версия для hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Наименование организации
     */
    @Column(name = "name", nullable = false, length = 125)
    @Refreshable
    private String name;

    /**
     * Полное наименование организации
     */
    @Column(name = "full_name", nullable = false, length = 225)
    @Refreshable
    private String fullName;

    /**
     * ИНН организации
     */
    @Column(name = "inn", nullable = false, length = 15, unique = true) //for INN of individual businessmen
    @Refreshable
    private String inn;

    /**
     * КПП организации
     */
    @Column(name = "kpp", nullable = false, length = 9)
    @Refreshable
    private String kpp;

    /**
     * Адрес организации
     */
    @Column(name = "address", nullable = false, length = 225)
    @Refreshable
    private String address;

    /**
     * Контактный телефон организцации
     */
    @Column(name = "phone", length = 25)
    @Refreshable
    private String phone;

    /**
     * Метка активности организации
     */
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
    @Refreshable
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
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

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
