package bellintegrator.com.demo.view.userdto;

import java.util.Date;

/**
 * Класс для представления иформации по конкретному пользователю
 *
 * @see bellintegrator.com.demo.entity.User
 */
public class SingleUserDto {

    /**
     * Идентификатор пользователя
     * @see bellintegrator.com.demo.entity.User#id
     */
    private Long id;

    /**
     * Имя пользователя
     * @see bellintegrator.com.demo.entity.User#firstName
     */
    private String firstName;

    /**
     * Фамилия пользователя
     * @see bellintegrator.com.demo.entity.User#lastName
     */
    private String secondName;

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
     * Номер для связи
     * @see bellintegrator.com.demo.entity.User#phone
     */
    private String phone;

    /**
     * Наименование документа пользователя
     * @see bellintegrator.com.demo.entity.DocumentType#name
     */
    private String docName;

    /**
     * Номер документа пользователя
     * @see bellintegrator.com.demo.entity.Document#docNumber
     */
    private String docNumber;

    /**
     * Дата документа пользователя
     * @see bellintegrator.com.demo.entity.Document#docDate
     */
    private Date docDate;

    /**
     * Название страны гражданства пользователя
     * @see bellintegrator.com.demo.entity.Country#name
     */
    private String citizenshipName;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
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
