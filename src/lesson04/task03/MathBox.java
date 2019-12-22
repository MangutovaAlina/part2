package lesson04.task03;

import java.util.*;

/**
 * доработанный класс MathBox
 */
public class MathBox extends ObjectBox<Number> {

    public MathBox(Number[] number) {
        this.setListObject(new HashSet<>(Arrays.asList(number)));
    }

    /**
     * метод summator возвращает сумму всех элементов коллекции
     *
     * @return число double - сумма
     */
    public Number summator() {
        Number summ = 0;
        Iterator<Number> iterator = this.getListObject().iterator();
        while (iterator.hasNext()) {
            Number value = iterator.next();
            if (summ instanceof Double || value instanceof Double) {
                summ = summ.doubleValue() + value.doubleValue();
            } else if (summ instanceof Float || value instanceof Float) {
                summ = summ.floatValue() + value.floatValue();
            } else if (summ instanceof Long || value instanceof Long) {
                summ = summ.longValue() + value.longValue();
            } else if (summ instanceof Integer || value instanceof Integer) {
                summ = summ.intValue() + value.intValue();
            } else if (summ instanceof Short || value instanceof Short) {
                summ = summ.shortValue() + value.shortValue();
            } else {
                summ = summ.byteValue() + value.byteValue();
            }
        }
        return summ;
    }

    /**
     * метод splitter выполняет поочередное деление на делитель всех элементов коллекции
     *
     * @param divider - делитель
     */
    public void splitter(Integer divider) {
        HashSet newMathSet = new HashSet<>();
        Iterator<Number> iterator = this.getListObject().iterator();
        while (iterator.hasNext()) {
            Number value = iterator.next();
            if (value instanceof Double) {
                newMathSet.add(value.doubleValue() / divider);
            } else if (value instanceof Float) {
                newMathSet.add(value.floatValue() / divider);
            } else if (value instanceof Long) {
                newMathSet.add(value.longValue() / divider);
            } else if (value instanceof Integer) {
                newMathSet.add(value.intValue() / divider);
            } else if (value instanceof Short) {
                newMathSet.add(value.shortValue() / divider);
            } else {
                newMathSet.add(value.byteValue() / divider);
            }
        }

        this.setListObject(newMathSet);
    }

    /**
     * переопределяем equals для класса MathBox
     *
     * @param object - входной объект, который сравниваем с существующим
     * @return - булевое значение
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        lesson04.task01.MathBox objectMathBox = (lesson04.task01.MathBox) object;
        return this.getListObject().equals(objectMathBox);
    }

    /**
     * переопределяем hashCode для класса MathBox
     *
     * @return возвращает хэшкод
     */
    @Override
    public int hashCode() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID.hashCode();
    }

    /**
     * переопределяем строку вывода для класса MathBox
     *
     * @return возвращает элементы листа в столбик
     */
    @Override
    public String toString() {
        return "элементы MathBox: " + this.getListObject().toString() + '}';
    }
}
