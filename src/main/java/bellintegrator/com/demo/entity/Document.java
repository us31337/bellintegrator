package bellintegrator.com.demo.entity;

import bellintegrator.com.demo.annotaion.Refreshable;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс для описания сущности документа
 */
@Entity
@Table(name = "document")
public class Document {

    /**
     * Первичный ключ
     */
    @Id
    @Column(name = "user_id")
    private Long id;

    /**
     * Версия для hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Номер документа
     */
    @Column(name = "doc_number", nullable = false, length = 10)
    @Refreshable
    private String docNumber;

    /**
     * Дата выдачи документа
     */
    @Column(name = "doc_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @Refreshable
    private Date docDate;

    /**
     * Тип документа
     *
     * @see DocumentType
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type", referencedColumnName = "id", nullable = false)
    @Refreshable
    private DocumentType type;

    /**
     * Владелец документа, сслыка на пользователя
     * @see User
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @Refreshable
    private User user;

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

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
