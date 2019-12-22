package lesson04.task03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * класс ObjectBox
 * с методами addObject, deleteObject, dump
 */
public class ObjectBox<T> {
    private HashSet<T> listObject;

    public HashSet<T> getListObject() {
        return listObject;
    }

    public ObjectBox(HashSet<T> listObject) {
        this.listObject = listObject;
    }

    public ObjectBox() {
        this.listObject = new HashSet<>();
    }

    public void setListObject(HashSet<T> listObject) {
        this.listObject = listObject;
    }

    /**
     * метод addObject добавляет Объект в лист объектов
     *
     * @param object - объект, который надо добавить
     */
    public boolean addObject(T object) {
        return listObject.add(object);
    }

    /**
     * метод deleteObject удаляет Объект из листа объектов, если он там есть
     *
     * @param object - объект, который надо удалить
     */
    public void deleteObject(T object) {
        Iterator<T> iterator = listObject.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(object)) {
                iterator.remove();
            }
        }
    }

    /**
     * метод dump выводит лист объектов в строчку
     */
    public String dump() {
        StringBuilder stringBuilder = new StringBuilder();

        Iterator<T> iterator = listObject.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next().toString());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

}