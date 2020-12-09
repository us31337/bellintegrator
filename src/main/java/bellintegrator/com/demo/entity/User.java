package bellintegrator.com.demo.entity;

import bellintegrator.com.demo.annotaion.Refreshable;

import javax.persistence.*;

/**
 * Класс для описания сущности пользователя
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * Первичный ключ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Версия для hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Имя пользвоателя
     */
    @Column(name = "first_name", length = 100, nullable = false)
    @Refreshable
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name", length = 100)
    @Refreshable
    private String lastName;

    /**
     * Отчество пользователя
     */
    @Column(name = "middle_name", length = 100)
    @Refreshable
    private String middleName;

    /**
     * Должность пользователя
     */
    @Column(name = "position", length = 100, nullable = false)
    @Refreshable
    private String position;

    /**
     * Телефон для связи
     */
    @Column(name = "phone", length = 25)
    @Refreshable
    private String phone;

    /**
     * Отметка идентификации полььзователя
     */
    @Column(name = "is_identified", nullable = false)
    @Refreshable
    private Boolean isIdentified;

    /**
     * Документ пользователя
     *
     * @see Document
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Refreshable
    private Document document;

    /**
     * Офис, к которому относиться пользователь
     * @see Office
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", referencedColumnName = "id", nullable = false)
    @Refreshable
    private Office office;

    /**
     * Страна гражданства
     * @see Country
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code", referencedColumnName = "id")
    @Refreshable
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean identified) {
        this.isIdentified = identified;
    }
}
