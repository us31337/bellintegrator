package bellintegrator.com.demo.entity;

import bellintegrator.com.demo.annotaion.Refreshable;

import javax.persistence.*;

/**
 * Класс для описания сущности страны
 */
@Entity
@Table (name = "country")
public class Country {

    /**
     * Первичный ключ
     */
    @Id
    @GeneratedValue @Column(name = "id")
    private Long id;

    /**
     * Версия для hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Код страны
     */
    @Refreshable
    @Column(name = "code", unique = true)
    private Integer code;

    /**
     * Название страны
     */
    @Refreshable
    @Column(name = "name", length = 100)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
