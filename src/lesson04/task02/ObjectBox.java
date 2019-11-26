package lesson04.task02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * класс ObjectBox
 * с методами addObject, deleteObject, dump
 */
public class ObjectBox {
    List<Object> listObject = new ArrayList<>();

    public List<Object> getListObject() {

        return listObject;
    }

    public void setListObject(List<Object> listObject) {

        this.listObject = listObject;
    }

    /**
     * метод addObject добавляет Объект в лист объектов
     *
     * @param object - объект, который надо добавить
     */
    public void addObject(Object object) {

        listObject.add(object);
    }

    /**
     * метод deleteObject удаляет Объект из листа объектов, если он там есть
     *
     * @param object - объект, который надо удалить
     */
    public void deleteObject(Object object) {
        Iterator iteratorListObject = listObject.iterator();
        while (iteratorListObject.hasNext()) {
            Object nextObject = iteratorListObject.next();
            if (nextObject.equals(object)) {
                iteratorListObject.remove();
            }
        }
    }

    /**
     * метод dump выводит лист объектов в строчку
     */
    public String dump() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < listObject.size(); i++) {
            stringBuilder.append(" элемент " + i + ": ");
            stringBuilder.append(listObject.get(i));
        }
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    /**
     * переопределяем метод equals, чтобы использовать его в удалении
     *
     * @param object - объект, который сраниваем
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ObjectBox objectBox = (ObjectBox) object;

        return listObject.equals(objectBox.listObject);
    }
}
