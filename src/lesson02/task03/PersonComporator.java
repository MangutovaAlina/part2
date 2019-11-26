package lesson02.task03;

import java.util.Comparator;

/**
 * классы корпораторов, которые позволяют сравнить две персоны
 *
 * PersonComporator - по полу, возрасту, имени
 *
 * PersonNameComparator - по имени
 * PersonAgeComparator - по возрасту
 * PersonSexComparator - по полу
 */
public class PersonComporator implements Comparator<Person> {
    public int compare(Person a, Person b) {
        Comparator<Person> pcomp = new PersonSexComparator().thenComparing(new PersonAgeComparator().thenComparing(new PersonNameComparator()));
        return pcomp.compare(a, b);
    }
}

class PersonNameComparator implements Comparator<Person> {
    public int compare(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }
}

class PersonAgeComparator implements Comparator<Person> {
    public int compare(Person a, Person b) {
        if (a.getAge() < b.getAge())
            return 1;
        else if (a.getAge() > b.getAge())
            return -1;
        else
            return 0;
    }
}

class PersonSexComparator implements Comparator<Person> {
    public int compare(Person a, Person b) {
        return a.getSex().compareTo(b.getSex());
    }
}
