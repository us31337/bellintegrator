package bellintegrator.com.demo.view.filter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OfficeFilter {

    @NotNull(message = "Id is required")
    @Min(value = 1, message = "Id cannot be negative")
    private Long orgId;
    private String name;
    private String phone;
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
