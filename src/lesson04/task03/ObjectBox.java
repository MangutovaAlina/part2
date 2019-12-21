package lesson04.task03;

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
    public void addObject(Object object) throws MyException {

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
     * метод dump переписан так, чтобы выводить любой лист
     *
     * @param listObj - любой лист
     */
    public void dump(List<?> listObj) {
        for (Iterator<?> i = listObj.iterator(); i.hasNext(); ) {
            Object object = i.next();
            System.out.println(object);
        }
    }

}