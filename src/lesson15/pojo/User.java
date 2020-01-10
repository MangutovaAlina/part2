package lesson15.pojo;

import java.sql.Date;

/**
 * класс, как аналог строки таблицы User
 */
public class User {
    private Integer id;
    private String name;
    private Date birthday;
    private String login_id;
    private String city;
    private String email;
    private String description;

    /**
     * конструкторы класса User
     */
    public User() {
    }

    public User(String name, Date birthday, String login_id, String city, String email, String description) {
        this.name = name;
        this.birthday = birthday;
        this.login_id = login_id;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    public User(Integer id, String name, Date birthday, String login_id, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.login_id = login_id;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    /**
     * геттеры
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    @Override
    /** переопределяем вывод нашей таблицы
     */
    public String toString() {
        return "строка таблица User(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", login_id=" + login_id +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' + ')';
    }
}