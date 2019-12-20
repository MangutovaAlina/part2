package lesson02.task03;

public class Person {
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

    /**
     * переопределяем сравнние персон
     *
     * @param obj объект класса Person
     * @return false или true
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Person person = (Person) obj;

        if ((getAge() == person.getAge())
                && (getName() == person.getName())
                && (getSex() == person.getSex())) {
            return true;
        } else {
            return false;
        }
    }
}
