package bellintegrator.com.demo.entity;

import bellintegrator.com.demo.annotaion.Refreshable;

import javax.persistence.*;

@Entity
@Table(name = "office")
public class Office {

    @Id @GeneratedValue @Column(name = "id")
    private Long officeId;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "name", length = 255, nullable = false)
    @Refreshable
    private String name;

    @Column(name = "address", length = 255, nullable = false)
    @Refreshable
    private String address;

    @Column(name = "phone", length = 25)
    @Refreshable
    private String phone;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
    @Refreshable
    private Boolean isActive;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Organisation getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(Organisation parentOrg) {
        this.parentOrg = parentOrg;
    }

}
