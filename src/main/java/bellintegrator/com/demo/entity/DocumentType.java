package bellintegrator.com.demo.entity;

import bellintegrator.com.demo.annotaion.Refreshable;

import javax.persistence.*;

/**
 * Класс для описания сущности типа документа (паспорт, свидетельство о рождении и т.д.)
 */
@Entity
@Table(name = "doc_type")
public class DocumentType {

    /**
     * Первичный ключ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private Long id;

    /**
     * Версия для hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Двухзначный код типа документа
     */
    @Column(name = "code", length = 2, nullable = false, unique = true)
    @Refreshable
    private String code;

    /**
     * Наименование типа документа
     */
    @Column(name = "name", nullable = false, length = 150)
    @Refreshable
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
