package lesson15;

/** класс, как аналог строки таблицы Role
 */
public class Role {
    public Integer id;
    public EnumName name;
    public String description;

    /** конструктор класса роли, входные параметры поля таблицы ROLE
     * @param id
     * @param name
     * @param description
     */
    public Role(Integer id, EnumName name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /** геттеры
     *  @return
     */
    public Integer getId() {
        return id;
    }

    public EnumName getName() {
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

