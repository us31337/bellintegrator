package bellintegrator.com.demo.view.filter;

import javax.validation.constraints.NotEmpty;

public class OrganisationFilter {
    @NotEmpty(message = "Name field is required")
    private String name;
    private String inn;
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

}
