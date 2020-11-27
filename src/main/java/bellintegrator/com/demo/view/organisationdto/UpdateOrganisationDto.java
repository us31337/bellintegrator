package bellintegrator.com.demo.view.organisationdto;

import javax.validation.constraints.*;

public class UpdateOrganisationDto {
    @NotNull
    @Min(1)
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Minimum 2 characters name required")
    private String name;

    @NotEmpty
    @Size(min = 2, message = "Minimum 2 characters full name required")
    private String fullName;

    @NotEmpty
    @Size(min = 10, message = "Minimum 10 digits required for INN")
    @Pattern(regexp = "^[\\d]+")
    private String inn;

    @NotEmpty
    @Size(min = 5, message = "Minimum 5 digits required for KPP")
    @Pattern(regexp = "^[\\d]+")
    private String kpp;

    @NotEmpty
    @Size(min = 5, message = "Minimum 5 digits required for KPP")
    private String address;
    private String phone;
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
