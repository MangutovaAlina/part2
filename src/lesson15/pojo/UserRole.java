package lesson15.pojo;

/**
 * класс, как аналог строки таблицы UserRole
 */
public class UserRole {
    private Integer id;
    private Integer user_id;
    private Integer role_id;

    public UserRole(Integer user_id, Integer role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public UserRole(Integer id, Integer user_id, Integer role_id) {
        this.id = id;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    /**
     * геттеры
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    @Override
    /** переопределяем вывод нашей таблицы
     */
    public String toString() {
        return "строка таблица UserRole(" +
                "id=" + id +
                ", user_id=" + user_id +
                ", role_id=" + role_id + ')';
    }
}


