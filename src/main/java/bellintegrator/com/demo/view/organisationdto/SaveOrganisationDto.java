package bellintegrator.com.demo.view.organisationdto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SaveOrganisationDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, message = "Minimum 2 characters name required")
    private String name;

    @NotEmpty(message = "Full name cannot be empty")
    @Size(min = 2, message = "Minimum 2 characters full name required")
    private String fullName;

    @NotEmpty(message = "INN cannot be empty")
    @Size(min = 10, message = "Minimum 10 digits required for INN")
    @Pattern(regexp = "^[\\d]+", message = "Only digits allowed in INN")
    private String inn;

    @NotEmpty(message = "KPP cannot be empty")
    @Size(min = 5, message = "Minimum 5 digits required for KPP")
    @Pattern(regexp = "^[\\d]+", message = "Only digits allowed in KPP")
    private String kpp;

    @NotEmpty(message = "Address cannot be empty")
    private String address;
    private String phone;
    private Boolean isActive;

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
