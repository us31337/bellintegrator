package bellintegrator.com.demo.view.reference;

/**
 * Класс для представления иформации для отображения данных справочника типов документов
 *
 * @see bellintegrator.com.demo.entity.DocumentType
 */
public class DocumentReferenceView {
    /**
     * Наименование типа документа
     * @see bellintegrator.com.demo.entity.DocumentType#name
     */
    private String name;

    /**
     * Код типа документа
     * @see bellintegrator.com.demo.entity.DocumentType#code
     */
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
