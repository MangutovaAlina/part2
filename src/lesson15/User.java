package lesson15;

import java.time.LocalDate;

/**  класс, как аналог строки таблицы User
 */
public class User {
    public Integer id;
    public String name;
    public LocalDate birthday;
    public Integer login_id;
    public String city;
    public String email;
    public String description;

    /** конструктор класса юзер, входные параметры поля таблицы USER
     *  @param id
     *  @param name
     *  @param birthday
     *  @param login_id
     *  @param city
     *  @param email
     *  @param description
     */
    public User(Integer id, String name, LocalDate birthday, Integer login_id, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.login_id = login_id;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    /** геттеры
     *  @return
     */
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Integer getLogin_id() {
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

