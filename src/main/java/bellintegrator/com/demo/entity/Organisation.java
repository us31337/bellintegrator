package bellintegrator.com.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "organisation")
public class Organisation {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "name", nullable = false, length = 125)
    private String name;

    @Column(name = "full_name", nullable = false, length = 225)
    private String fullName;

    @Column(name = "inn", nullable = false, length = 15, unique = true) //for INN of individual businessmen
    private String inn;

    @Column(name = "kpp", nullable = false, length = 9)
    private String kpp;

    @Column(name = "address", nullable = false, length = 225)
    private String address;

    @Column(name = "phone", length = 25)
    private String phone;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
