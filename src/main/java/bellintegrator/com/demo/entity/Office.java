package bellintegrator.com.demo.entity;

import bellintegrator.com.demo.annotaion.Refreshable;

import javax.persistence.*;

/**
 * Класс для сущности офиса
 */
@Entity
@Table(name = "office")
public class Office {

    /**
     * Первичный ключ
     */
    @Id @GeneratedValue @Column(name = "id")
    private Long officeId;

    /**
     * Версия для hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Название офиса
     */
    @Column(name = "name", length = 255, nullable = false)
    @Refreshable
    private String name;

    /**
     * Адрес офиса
     */
    @Column(name = "address", length = 255, nullable = false)
    @Refreshable
    private String address;

    /**
     * Номер телефона
     */
    @Column(name = "phone", length = 25)
    @Refreshable
    private String phone;

    /**
     * Пометка активности
     */
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
    @Refreshable
    private Boolean isActive;

    /**
     * Организаци, к которой офис относится
     *
     * @see Organisation
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
    @Refreshable
    private Organisation parentOrg;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
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

    public Organisation getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(Organisation parentOrg) {
        this.parentOrg = parentOrg;
    }

}
