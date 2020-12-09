package bellintegrator.com.demo.view.userdto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Класс для представления сохраняемой иформации по пользователю
 *
 * @see bellintegrator.com.demo.entity.User
 */
public class SaveUserDto {

    /**
     * Идентификатор офиса, к которому принадлежит пользователь
     *
     * @see bellintegrator.com.demo.entity.Office#officeId
     */
    @Min(value = 1, message = "Office id cannot be null or negative")
    private Long officeId;

    /**
     * Имя пользователя
     * @see bellintegrator.com.demo.entity.User#firstName
     */
    @Size(min = 2, message = "First name too short")
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
    @NotEmpty(message = "Position cannot be empty")
    private String position;

    /**
     * Номер для связи
     * @see bellintegrator.com.demo.entity.User#phone
     */
    private String phone;

    /**
     * Код типа документа пользователя
     * @see bellintegrator.com.demo.entity.DocumentType#code
     */
    @Size(min = 2, message = "Doc code contains 2 digits")
    private String docCode;

    /**
     * Наименование документа пользователя
     * @see bellintegrator.com.demo.entity.DocumentType#name
     */
    private String docName;

    /**
     * Дата документа пользователя
     * @see bellintegrator.com.demo.entity.Document#docDate
     */
    private Date docDate;

    /**
     * Номер документа пользователя
     * @see bellintegrator.com.demo.entity.Document#docNumber
     */
    private String docNumber;


    /**
     * Код страны гражданства пользователя
     * @see bellintegrator.com.demo.entity.Country#code
     */
    private Integer citizenshipCode;

    /**
     * Отметка идентификации пользователя
     * @see bellintegrator.com.demo.entity.User#isIdentified
     */
    private Boolean isIdentified;

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

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
