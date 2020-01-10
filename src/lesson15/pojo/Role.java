package lesson15.pojo;

/**
 * класс, как аналог строки таблицы Role
 */
public class Role {
    private Integer id;
    private String name;
    private String description;

    public Role() {
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    @Override
    /** переопределяем вывод нашей таблицы
     */
    public String toString() {
        return "строка таблица Role( " +
                "id=" + id +
                ", name=" + name +
                ", description='" + description + '\'' + ')';
    }
}

