package bellintegrator.com.demo.view.filter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OfficeFilter {

    @NotNull(message = "Id is required")
    @Min(1)
    private Long orgId;
    private String name;
    private String phone;
    private Boolean isActive;

    public OfficeFilter(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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