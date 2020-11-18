package bellintegrator.com.demo.entity;

import bellintegrator.com.demo.annotaion.Refreshable;

import javax.persistence.*;

@Entity
@Table (name = "country")
public class Country {

    @Id
    @GeneratedValue @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Refreshable
    @Column(name = "code", unique = true)
    private int code;

    @Refreshable
    @Column(name = "name", length = 100)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
