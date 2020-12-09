package bellintegrator.com.demo.view.reference;

/**
 * Класс для представления иформации для отображения данных справочника стран
 *
 * @see bellintegrator.com.demo.entity.Country
 */
public class CountryReferenceView {
    /**
     * Наименование страны
     * @see bellintegrator.com.demo.entity.Country#name
     */
    private String name;

    /**
     * Код страны
     * @see bellintegrator.com.demo.entity.Country#code
     */
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
