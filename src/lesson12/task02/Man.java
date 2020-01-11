package lesson12.task02;

public class Man implements Person {
    private int age;
    private String name;

    public Man(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void personHello(String name) {
        System.out.println("My name is " + this.name + ". I'm " + this.age + " years old");
    }
}
