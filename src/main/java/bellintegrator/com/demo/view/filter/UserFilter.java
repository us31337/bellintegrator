package bellintegrator.com.demo.view.filter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Класс фильтра для поиска пользователей
 *
 * @see bellintegrator.com.demo.entity.User
 */
public class UserFilter {

    /**
     * Первичный ключ офиса, к которому принадлежит пользователь
     * @see bellintegrator.com.demo.entity.Office#officeId
     */
    @NotNull(message = "Office id is required")
    @Min(value = 1, message = "Id cannot be negative")
    private Long officeId;
    /**
     * Имя пользователя
     * @see bellintegrator.com.demo.entity.User#firstName
     */
    private String firstName;
    /**
     * Фамилия пользователя
     * @see bellintegrator.com.demo.entity.User#lastName
     */
    private String lastName;
    /**
     * Отчество пользователя
     * @see bellintegrator.com.demo.entity.User#middleName
     */
    private String middleName;
    /**
     * Должность пользователя
     * @see bellintegrator.com.demo.entity.User#position
     */
    private String position;
    /**
     * Код типа документа пользователя
     * @see bellintegrator.com.demo.entity.DocumentType#code
     */
    private String docCode;
    /**
     * Код страны гражданства пользователя
     * @see bellintegrator.com.demo.entity.Country#id
     */
    private Integer citizenshipCode;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
