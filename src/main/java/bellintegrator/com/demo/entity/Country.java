package bellintegrator.com.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "country")
public class Country {

    @Id
    @GeneratedValue @Column(name = "id")
    private long id;

    @Column(name = "code", unique = true)
    private int code;

    @Version
    @Column(name = "version")
    private int version;

    @Column(name = "name", length = 100)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
