package lesson08.task02;

import java.io.Serializable;

/**
 * класс Person, где пол - это энам
 */
public class Person implements Serializable {
    private int age;
    private String name;
    private Sex sex;

    public Person(int age, String name, Sex sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    /**
     * переопределяем строку вывода для класса Person
     *
     * @return строка вида Sex:  Age:  Name:
     */
    @Override
    public String toString() {
        return "Sex: " + sex + " Age: " + age + " Name: " + name + "\n";
    }
}


