package bellintegrator.com.demo.view.officedto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveOfficeDto {

    @NotNull(message = "Organisation id is required")
    @Min(value = 1, message = "Id cannot be negative")
    private Long orgId;

    @Size(min = 2, message = "Name too short")
    private String name;

    @Size(min = 5, message = "Address too short")
    private String address;

    private String phone;
    private Boolean isActive;

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
