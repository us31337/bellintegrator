package bellintegrator.com.demo.view.userdto;

/**
 * Класс для представления иформации по списку пользователей
 *
 * @see bellintegrator.com.demo.entity.User
 */
public class ListUserDto {
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
    private String position;

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

}
