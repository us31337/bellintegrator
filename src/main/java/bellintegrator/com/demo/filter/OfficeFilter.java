package bellintegrator.com.demo.filter;

public class OfficeFilter {
    private Long orgId;
    private String name;
    private String phone;
    private Boolean isActive;

    public OfficeFilter(Long orgId) {
        if (orgId != null && orgId > 0) {
            this.orgId = orgId;
        } else {
            throw new IllegalArgumentException("Organisation id cannot be negative or empty");
        }
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) { //do we really need setter here?
        if (orgId != null && orgId > 0) {
            this.orgId = orgId;
        } else {
            throw new IllegalArgumentException("Organisation id cannot be negative or empty");
        }
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
